package com.zjrfid.materialsmanage.rfid;

import android.os.Message;

import java.util.List;
import java.util.Random;

import com.hiklife.rfidapi.AntennaPortConfiguration;
import com.hiklife.rfidapi.MemoryBank;
import com.hiklife.rfidapi.RadioCtrl;
import com.hiklife.rfidapi.ReadParms;
import com.hiklife.rfidapi.ReadResult;
import com.hiklife.rfidapi.Session;
import com.hiklife.rfidapi.SingulationAlgorithm;
import com.hiklife.rfidapi.SingulationAlgorithmParms;
import com.hiklife.rfidapi.SingulationCriteria;
import com.hiklife.rfidapi.SingulationCriteriaStatus;
import com.hiklife.rfidapi.TagOperResult;
import com.hiklife.rfidapi.WriteParms;
import com.hiklife.rfidapi.ctrlOperateResult;
import com.hiklife.rfidapi.matchType;
import com.hiklife.rfidapi.radioBusyException;
import com.hiklife.rfidapi.radioFailException;
import com.hiklife.rfidapi.tagMemoryOpResult;


public class RfidOperation {


    public static final RadioCtrl myRadio = new RadioCtrl();
    public static AntennaPortConfiguration config = new AntennaPortConfiguration();


    public static void setAntennaPower(int power) {
        if (power > 30 || power < 0) {
            config.powerLevel = 300;

        } else {

            config.powerLevel = power * 10;

        }
    }

    public static int connectRadio() {
        int returnValue = -1;
        try {
            if (myRadio.ConnectRadio("/dev/ttysWK2"/*"/dev/ttyS2" */, 115200) == ctrlOperateResult.OK) {
                // 配置天线参数

                config.dwellTime = 200;
                config.numberInventoryCycles = 8192;
//					config.powerLevel = 300;

                // 配置单化算法为动态Q值，以及通话选择为Session1，翻转关闭减少标签的上报次数
                SingulationAlgorithmParms parm = new SingulationAlgorithmParms();
                parm.toggleTarget = 0;
                parm.maxQValue = 15;
                parm.minQValue = 0;
                parm.qValue = 7;
                parm.startQValue = 7;
                parm.repeatUntilNoTags = 0;
                parm.thresholdMultiplier = 4;
                parm.retryCount = 0;
                parm.singulationAlgorithmType = SingulationAlgorithm.DYNAMICQ;
                try {
                    try {
                        myRadio.GetAntennaSWR(0, config.powerLevel);
                    } catch (radioFailException e) {
                    }

                    if (myRadio.SetAntennaPortConfiguration(0, config) == ctrlOperateResult.OK
                            && myRadio.SetCurrentLinkProfile(1) == ctrlOperateResult.OK
                            && myRadio.SetTagGroupSession(Session.S1) == ctrlOperateResult.OK
                            && myRadio.SetCurrentSingulationAlgorithm(parm) == ctrlOperateResult.OK) {

                        returnValue = 0;

                    } else {

                        returnValue = 2;
                    }

                    myRadio.WaveCtrlOff(0);

                } catch (radioBusyException e) {

                    returnValue = -2;

                } catch (radioFailException e) {

                    returnValue = -2;
                }
            } else {

                returnValue = -1;
            }

        } catch (radioBusyException e) {
            e.printStackTrace();
            returnValue = -2;
        }

        return returnValue;
    }

    public static int DisconnectRadio() {
        int value = -1;
        try {
            if (myRadio.DisconnectRadio() == ctrlOperateResult.OK) {
                value = 1;
            } else {
                value = 0;
            }
        } catch (radioBusyException e) {
            e.printStackTrace();
            value = -1;
        }
        return value;
    }

    //选择指定Epc的用户区
    public static Result readGivenUser(String Epc, short offset, short length) {

        Result result = new Result();

        if (true) {
            try {
                String maskValue = Epc;
                if (maskValue.equals("")) {
                    result.SingulationCriteriaInfo = "未选择要操作的标签";
                    result.success = false;
                    return result;
                }
                SingulationCriteria singulationCriteria = new SingulationCriteria();
                singulationCriteria.status = SingulationCriteriaStatus.Enabled;
                singulationCriteria.offset = 0;
                singulationCriteria.count = maskValue.length() * 4;
                singulationCriteria.match = matchType.Regular;

                for (int i = 0; i < (maskValue.length() / 2); i++) {
                    singulationCriteria.mask[i] = (byte) (Short.parseShort(
                            maskValue.substring(i * 2, i * 2 + 2), 16) & 0x00FF);
                }
                ctrlOperateResult ctrlresult;
                ctrlresult = myRadio.Set18K6CPostMatchCriteria(singulationCriteria);
                if (ctrlresult != ctrlOperateResult.OK) {
                    result.SingulationCriteriaInfo = ctrlresult.toString();
                    result.success = false;
                    return result;
                }
            } catch (radioBusyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result.SingulationCriteriaInfo = "radio busy";
                result.success = false;
                return result;
            }

        }

        try {
            ReadParms parms = new ReadParms();
            parms.memBank = MemoryBank.USER;
            parms.offset = offset;
            parms.length = length;
            parms.accesspassword = 0;
            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);

            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {

                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);

                        }

                        result.readInfo = exChange(result.readInfo);
                        result.success = true;
                    }
                } else {

                    result.reportInfo = "标签user区读取出错";
                    result.success = false;
                }
            } else {

                result.reportInfo = "未发现任何标签";
                result.success = false;
            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;

        } catch (radioFailException e) {

            result.reportInfo = "操作失败";
            result.success = false;
        } catch (Exception e) {

            result.reportInfo = "操作失败";
            result.success = false;
        }

        if (true) {
            try {
                SingulationCriteria dissingulationCriteria = new SingulationCriteria();
                dissingulationCriteria.status = SingulationCriteriaStatus.Disabled;
                dissingulationCriteria.offset = 0;
                dissingulationCriteria.count = 0;
                dissingulationCriteria.match = matchType.Regular;

                ctrlOperateResult ctrlResult = myRadio.Set18K6CPostMatchCriteria(dissingulationCriteria);
                if (ctrlResult != ctrlOperateResult.OK) {

                    result.SingulationCriteriaInfo = ctrlResult.toString();

                }
            } catch (radioBusyException e) {

                result.SingulationCriteriaInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            }
        }
        return result;
    }

    public static Result readGivenTid(String Epc, short offset, short length) {

        Result result = new Result();

        if (true) {
            String maskValue = Epc;
            if (maskValue.equals("")) {
                result.SingulationCriteriaInfo = "未选择要操作的标签";
                result.success = false;
                return result;
            }
            SingulationCriteria singulationCriteria = new SingulationCriteria();
            singulationCriteria.status = SingulationCriteriaStatus.Enabled;
            singulationCriteria.offset = 0;
            singulationCriteria.count = maskValue.length() * 4;
            singulationCriteria.match = matchType.Regular;

            for (int i = 0; i < (maskValue.length() / 2); i++) {
                singulationCriteria.mask[i] = (byte) (Short.parseShort(
                        maskValue.substring(i * 2, i * 2 + 2), 16) & 0x00FF);
            }
            ctrlOperateResult ctrlresult;
            try {
                ctrlresult = myRadio.Set18K6CPostMatchCriteria(singulationCriteria);
                if (ctrlresult != ctrlOperateResult.OK) {
                    result.SingulationCriteriaInfo = ctrlresult.toString();
                    result.success = false;
                    return result;
                }
            } catch (radioBusyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result.SingulationCriteriaInfo = "radio busy";
                result.success = false;
                return result;
            }

        }

        try {
            ReadParms parms = new ReadParms();
            parms.memBank = MemoryBank.TID;
            parms.offset = offset;
            parms.length = length;
            parms.accesspassword = 0;
            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);
            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {

                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);

                        }

                        result.readInfo = exChange(result.readInfo);
                        result.success = true;
                    }
                } else {

                    result.reportInfo = "标签tid区读取出错";
                    result.success = false;
                }
            } else {

                result.reportInfo = "未发现任何标签";
                result.success = false;
            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;

        } catch (radioFailException e) {

            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {

            result.reportInfo = "操作失败";
            result.success = false;

        }


        if (true) {
            try {
                SingulationCriteria dissingulationCriteria = new SingulationCriteria();
                dissingulationCriteria.status = SingulationCriteriaStatus.Disabled;
                dissingulationCriteria.offset = 0;
                dissingulationCriteria.count = 0;
                dissingulationCriteria.match = matchType.Regular;

                ctrlOperateResult ctrlResult = myRadio.Set18K6CPostMatchCriteria(dissingulationCriteria);
                if (ctrlResult != ctrlOperateResult.OK) {
                    result.SingulationCriteriaInfo = ctrlResult.toString();
                }
            } catch (radioBusyException e) {
                result.SingulationCriteriaInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            }
        }
        return result;
    }

    public static Result readGivenEpc(String Epc, short offset, short length) {

        Result result = new Result();

        if (true) {
            try {
                String maskValue = Epc;
                if (maskValue.equals("")) {
                    result.SingulationCriteriaInfo = "未选择要操作的标签";
                    result.success = false;
                    return result;
                }
                SingulationCriteria singulationCriteria = new SingulationCriteria();
                singulationCriteria.status = SingulationCriteriaStatus.Enabled;
                singulationCriteria.offset = 0;
                singulationCriteria.count = maskValue.length() * 4;
                singulationCriteria.match = matchType.Regular;

                for (int i = 0; i < (maskValue.length() / 2); i++) {
                    singulationCriteria.mask[i] = (byte) (Short.parseShort(
                            maskValue.substring(i * 2, i * 2 + 2), 16) & 0x00FF);
                }
                ctrlOperateResult ctrlresult;
                ctrlresult = myRadio.Set18K6CPostMatchCriteria(singulationCriteria);
                if (ctrlresult != ctrlOperateResult.OK) {
                    result.SingulationCriteriaInfo = ctrlresult.toString();
                    result.success = false;
                    return result;
                }
            } catch (radioBusyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result.SingulationCriteriaInfo = "radio busy";
                result.success = false;
                return result;
            }

        }

        try {
            ReadParms parms = new ReadParms();
            parms.memBank = MemoryBank.EPC;
            parms.offset = offset;
            parms.length = length;
            parms.accesspassword = 0;
            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);

            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {

                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);

                        }

                        result.readInfo = exChange(result.readInfo);
                        result.success = true;
                    }
                } else {

                    result.reportInfo = "标签Epc区读取出错";
                    result.success = false;
                }
            } else {

                result.reportInfo = "未发现任何标签";
                result.success = false;
            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;

        } catch (radioFailException e) {

            result.reportInfo = "操作失败";
            result.success = false;
        } catch (Exception e) {

            result.reportInfo = "操作失败";
            result.success = false;
        }

        if (true) {
            try {
                SingulationCriteria dissingulationCriteria = new SingulationCriteria();
                dissingulationCriteria.status = SingulationCriteriaStatus.Disabled;
                dissingulationCriteria.offset = 0;
                dissingulationCriteria.count = 0;
                dissingulationCriteria.match = matchType.Regular;

                ctrlOperateResult ctrlResult = myRadio.Set18K6CPostMatchCriteria(dissingulationCriteria);
                if (ctrlResult != ctrlOperateResult.OK) {

                    result.SingulationCriteriaInfo = ctrlResult.toString();

                }
            } catch (radioBusyException e) {

                result.SingulationCriteriaInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            }
        }
        return result;
    }


    public static Result readUnGivenUser(short offset, short length) {
        Result result = new Result();

        try {
            ReadParms parms = new ReadParms();
            parms.memBank = MemoryBank.USER;
            parms.offset = offset;
            parms.length = length;
            parms.accesspassword = 0;
            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);

            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {

                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);

                        }
                        result.readInfo = exChange(result.readInfo);
                        result.success = true;
                    }
                } else {

                    result.reportInfo = "标签user区读取出错";
                    result.success = false;
                }
            } else {

                result.reportInfo = "未发现任何标签";
                result.success = false;
            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;

        } catch (radioFailException e) {

            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {

            result.reportInfo = "操作失败";
            result.success = false;

        }

        return result;

    }

//    public static Result readUnGivenTid(short offset, short length) {
//        Result result = new Result();
//
//        try {
//            ReadParms parms = new ReadParms();
//            parms.memBank = MemoryBank.TID;
//            parms.offset = offset;
//            parms.length = length;
//            parms.accesspassword = 0;
//            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);
//
//            if (tagInfos.size() > 0) {
//                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
//                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
//                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {
//
//                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
//
//                        }
//                        result.readInfo = exChange(result.readInfo);
//                        result.success = true;
//                    }
//                } else {
//
//                    result.reportInfo = "标签tid区读取出错";
//                    result.success = false;
//                }
//            } else {
//
//                result.reportInfo = "未发现任何标签";
//                result.success = false;
//            }
//        } catch (radioBusyException e) {
//
//            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
//            result.success = false;
//
//        } catch (radioFailException e) {
//
//            result.reportInfo = "操作失败";
//            result.success = false;
//
//        } catch (Exception e) {
//
//            result.reportInfo = "操作失败";
//            result.success = false;
//
//        }
//
//        return result;
//
//    }

    public static Result readUnGivenTid(short offset, short length) {

        Result result = new Result();
        String[] rfid = {"68000ECDF800", "68001ECDF800", "68002ECDF800", "68003ECDF800", "68004ECDF800", "68005ECDF800", "68006ECDF800", "68007ECDF800"};

        int index = new Random().nextInt(8);
        result.setReportInfo("");
        result.setReadInfo(rfid[index]);
        result.setSingulationCriteriaInfo("");
        result.setSuccess(true);
        return result;
    }

    public static Result readUnGivenEpc(short offset, short length) {
        Result result = new Result();

        try {
            ReadParms parms = new ReadParms();
            parms.memBank = MemoryBank.EPC;
            parms.offset = offset;
            parms.length = length;
            parms.accesspassword = 0;
            List<ReadResult> tagInfos = myRadio.TagInfoRead(parms);

            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (tagInfos.get(tagInfos.size() - 1).readData != null) {
                        for (int i = 0; i < tagInfos.get(tagInfos.size() - 1).readData.length; i++) {

                            result.readInfo += Integer.toHexString(((tagInfos.get(tagInfos.size() - 1).readData[i] >> 8) & 0x000000FF) | 0xFFFFFF00).substring(6) + Integer.toHexString((tagInfos.get(tagInfos.size() - 1).readData[i] & 0x000000FF) | 0xFFFFFF00).substring(6);

                        }
                        result.readInfo = exChange(result.readInfo);
                        result.success = true;
                    }
                } else {

                    result.reportInfo = "标签Epc区读取出错";
                    result.success = false;
                }
            } else {

                result.reportInfo = "未发现任何标签";
                result.success = false;
            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;

        } catch (radioFailException e) {

            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {

            result.reportInfo = "操作失败";
            result.success = false;

        }

        return result;

    }


    public static Result WriteGivenUser(String Epc, String wData) {

        Result result = new Result();
        String maskValue = Epc;

        try {
            if (maskValue.equals("")) {
                result.SingulationCriteriaInfo = "未选择要操作的标签";
                result.success = false;
                return result;
            }
            SingulationCriteria singulationCriteria = new SingulationCriteria();
            singulationCriteria.status = SingulationCriteriaStatus.Enabled;
            singulationCriteria.offset = 0;
            singulationCriteria.count = maskValue.length() * 4;
            singulationCriteria.match = matchType.Regular;

            for (int i = 0; i < (maskValue.length() / 2); i++) {
                singulationCriteria.mask[i] = (byte) (Short.parseShort(
                        maskValue.substring(i * 2, i * 2 + 2), 16) & 0x00FF);
            }
            ctrlOperateResult ctrlresult;
            ctrlresult = myRadio.Set18K6CPostMatchCriteria(singulationCriteria);
            if (ctrlresult != ctrlOperateResult.OK) {
                result.SingulationCriteriaInfo = ctrlresult.toString();
                result.success = false;
                return result;
            }
        } catch (radioBusyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.SingulationCriteriaInfo = "radio busy";
            result.success = false;
            return result;
        }

        // 进行标签写入操作
        // 先判断输入的数据是否合法

        if ((wData.length() % 4) != 0) {
            result.reportInfo = "写入数据长度不是4的倍数";
            result.success = false;
        }
        if ((wData.length() / 4) == 0) {

            result.reportInfo = "写入数据长度不能小于1个字（4个字符）";
            result.success = false;
        }

        if (!checkStrIs16(wData)) {

            result.reportInfo = "数据存在非十六进制字符";
            result.success = false;
        }

        if (!result.reportInfo.isEmpty()) {

            return result;
        }

        try {
            WriteParms parms = new WriteParms();
            parms.memBank = MemoryBank.USER;
            parms.offset = 0;
            parms.length = (short) (wData.length() / 4);
            parms.accesspassword = 0;

            short[] writeBuf = new short[parms.length];

            for (int i = 0; i < writeBuf.length; i++) {
                writeBuf[i] = (short) (Integer.parseInt(wData.substring(i * 4, i * 4 + 4), 16) & 0x0000FFFF);
            }

            List<TagOperResult> tagInfos = myRadio.TagInfoWrite(parms, writeBuf);


            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (parms.memBank == MemoryBank.EPC) {
                        // 判断修改的部分是否在显示区域
                        if (parms.offset > 1 && parms.offset < maskValue.length() / 4 + 2) {
                            int replaceLength = (parms.length * 4) > maskValue.length() ? maskValue.length() : (parms.length * 4);
                            maskValue = maskValue.substring(0, (parms.offset - 2) * 4) + wData.substring(0, replaceLength) + maskValue.substring((parms.offset - 2) * 4 + replaceLength);

                        }
                    }

                    result.reportInfo = "成功向标签写入数据";
                    result.success = true;

                } else {
                    result.reportInfo = "标签写入出错";
                    result.success = false;
                }
            } else {
                result.reportInfo = "未发现任何标签";
                result.success = false;

            }
        } catch (radioBusyException e) {
            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;
        } catch (radioFailException e) {
            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {
            result.reportInfo = "操作失败";
            result.success = false;
        }

        try {
            SingulationCriteria dissingulationCriteria = new SingulationCriteria();
            dissingulationCriteria.status = SingulationCriteriaStatus.Disabled;
            dissingulationCriteria.offset = 0;
            dissingulationCriteria.count = 0;
            dissingulationCriteria.match = matchType.Regular;

            ctrlOperateResult result1 = myRadio.Set18K6CPostMatchCriteria(dissingulationCriteria);
            if (result1 != ctrlOperateResult.OK) {
                result.reportInfo = result.toString();


            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";

        }


        return result;

    }


    public static Result WriteGivenEpc(String Epc, String wData) {
        Result result = new Result();
        String maskValue = Epc;

        try {

            if (maskValue.equals("")) {
                result.SingulationCriteriaInfo = "未选择要操作的标签";
                result.success = false;
                return result;
            }
            SingulationCriteria singulationCriteria = new SingulationCriteria();
            singulationCriteria.status = SingulationCriteriaStatus.Enabled;
            singulationCriteria.offset = 0;
            singulationCriteria.count = maskValue.length() * 4;
            singulationCriteria.match = matchType.Regular;

            for (int i = 0; i < (maskValue.length() / 2); i++) {
                singulationCriteria.mask[i] = (byte) (Short.parseShort(
                        maskValue.substring(i * 2, i * 2 + 2), 16) & 0x00FF);
            }
            ctrlOperateResult ctrlresult;
            ctrlresult = myRadio.Set18K6CPostMatchCriteria(singulationCriteria);
            if (ctrlresult != ctrlOperateResult.OK) {
                result.SingulationCriteriaInfo = ctrlresult.toString();
                result.success = false;
                return result;
            }
        } catch (radioBusyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.SingulationCriteriaInfo = "radio busy";
            result.success = false;
            return result;
        }


        // 进行标签写入操作
        // 先判断输入的数据是否合法

        if ((wData.length() % 4) != 0) {
            result.reportInfo = "写入数据长度不是4的倍数";
            result.success = false;
        }
        if ((wData.length() / 4) == 0) {

            result.reportInfo = "写入数据长度不能小于1个字（4个字符）";
            result.success = false;
        }

        if (!checkStrIs16(wData)) {

            result.reportInfo = "数据存在非十六进制字符";
            result.success = false;
        }

        if (!result.reportInfo.isEmpty()) {
            return result;
        }


        try {
            WriteParms parms = new WriteParms();
            parms.memBank = MemoryBank.EPC;
            parms.offset = 2;
            parms.length = (short) (wData.length() / 4);
            parms.accesspassword = 0;

            short[] writeBuf = new short[parms.length];

            for (int i = 0; i < writeBuf.length; i++) {
                writeBuf[i] = (short) (Integer.parseInt(wData.substring(i * 4, i * 4 + 4), 16) & 0x0000FFFF);
            }

            List<TagOperResult> tagInfos = myRadio.TagInfoWrite(parms, writeBuf);


            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {
                    if (parms.memBank == MemoryBank.EPC) {
                        // 判断修改的部分是否在显示区域
                        if (parms.offset > 1 && parms.offset < maskValue.length() / 4 + 2) {
                            int replaceLength = (parms.length * 4) > maskValue.length() ? maskValue.length() : (parms.length * 4);
                            maskValue = maskValue.substring(0, (parms.offset - 2) * 4) + wData.substring(0, replaceLength) + maskValue.substring((parms.offset - 2) * 4 + replaceLength);

                        }
                    }

                    result.reportInfo = "成功向标签写入数据";
                    result.success = true;

                } else {
                    result.reportInfo = "标签写入出错";
                    result.success = false;
                }
            } else {
                result.reportInfo = "未发现任何标签";
                result.success = false;

            }
        } catch (radioBusyException e) {
            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;
        } catch (radioFailException e) {
            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {
            result.reportInfo = "操作失败";
            result.success = false;
        }

        try {
            SingulationCriteria dissingulationCriteria = new SingulationCriteria();
            dissingulationCriteria.status = SingulationCriteriaStatus.Disabled;
            dissingulationCriteria.offset = 0;
            dissingulationCriteria.count = 0;
            dissingulationCriteria.match = matchType.Regular;

            ctrlOperateResult result1 = myRadio.Set18K6CPostMatchCriteria(dissingulationCriteria);
            if (result1 != ctrlOperateResult.OK) {
                result.reportInfo = result.toString();


            }
        } catch (radioBusyException e) {

            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";

        }


        return result;

    }


    public static Result WriteUnGivenUser(String wData) {

        Result result = new Result();

        // 进行标签写入操作
        // 先判断输入的数据是否合法

        if ((wData.length() % 4) != 0) {
            result.reportInfo = "写入数据长度不是4的倍数";
            result.success = false;
        }
        if ((wData.length() / 4) == 0) {

            result.reportInfo = "写入数据长度不能小于1个字（4个字符）";
            result.success = false;
        }

        if (!checkStrIs16(wData)) {

            result.reportInfo = "数据存在非十六进制字符";
            result.success = false;
        }

        if (!result.reportInfo.isEmpty()) {

            return result;
        }

        try {
            WriteParms parms = new WriteParms();
            parms.memBank = MemoryBank.USER;
            parms.offset = 0;
            parms.length = (short) (wData.length() / 4);
            parms.accesspassword = 0;

            short[] writeBuf = new short[parms.length];

            for (int i = 0; i < writeBuf.length; i++) {
                writeBuf[i] = (short) (Integer.parseInt(wData.substring(i * 4, i * 4 + 4), 16) & 0x0000FFFF);
            }

            List<TagOperResult> tagInfos = myRadio.TagInfoWrite(parms, writeBuf);


            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {

                    result.reportInfo = "成功向标签写入数据";
                    result.success = true;

                } else {
                    result.reportInfo = "标签写入出错";
                    result.success = false;
                }
            } else {
                result.reportInfo = "未发现任何标签";
                result.success = false;

            }
        } catch (radioBusyException e) {
            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;
        } catch (radioFailException e) {
            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {
            result.reportInfo = "操作失败";
            result.success = false;
        }

        return result;

    }


    public static Result WriteUnGivenEpc(String wData) {

        Result result = new Result();

        // 进行标签写入操作
        // 先判断输入的数据是否合法

        if ((wData.length() % 4) != 0) {
            result.reportInfo = "写入数据长度不是4的倍数";
            result.success = false;
        }
        if ((wData.length() / 4) == 0) {

            result.reportInfo = "写入数据长度不能小于1个字（4个字符）";
            result.success = false;
        }

        if (!checkStrIs16(wData)) {

            result.reportInfo = "数据存在非十六进制字符";
            result.success = false;
        }

        if (!result.reportInfo.isEmpty()) {

            return result;
        }

        try {
            WriteParms parms = new WriteParms();
            parms.memBank = MemoryBank.EPC;
            parms.offset = 2;
            parms.length = (short) (wData.length() / 4);
            parms.accesspassword = 0;

            short[] writeBuf = new short[parms.length];

            for (int i = 0; i < writeBuf.length; i++) {
                writeBuf[i] = (short) (Integer.parseInt(wData.substring(i * 4, i * 4 + 4), 16) & 0x0000FFFF);
            }

            List<TagOperResult> tagInfos = myRadio.TagInfoWrite(parms, writeBuf);


            if (tagInfos.size() > 0) {
                if (tagInfos.get(tagInfos.size() - 1).result == tagMemoryOpResult.Ok) {

                    result.reportInfo = "成功向标签写入数据";
                    result.success = true;

                } else {
                    result.reportInfo = "标签写入出错";
                    result.success = false;
                }
            } else {
                result.reportInfo = "未发现任何标签";
                result.success = false;

            }
        } catch (radioBusyException e) {
            result.reportInfo = "模块正在处理其他事务(如盘点),请稍后重试";
            result.success = false;
        } catch (radioFailException e) {
            result.reportInfo = "操作失败";
            result.success = false;

        } catch (Exception e) {
            result.reportInfo = "操作失败";
            result.success = false;
        }

        return result;

    }


    public static String HexToAscii(String hexStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= hexStr.length() - 2; i += 2) {
            String s = hexStr.substring(i, i + 2);
            int j = HexStringtoInt(s);
            char c = (char) j;
            String str = String.valueOf(c);
            if (i == hexStr.length() - 2 && j == 0) {

            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static int HexStringtoInt(String str) {
        int i = Integer.parseInt(str, 16);
        return i;
    }

    public static String AddDot(String TidStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= TidStr.length() - 4; i += 4) {
            String str = TidStr.substring(i, i + 4);
            if (i == (TidStr.length() - 4)) {
                sb.append(str);
            } else {
                sb.append(str + ".");
            }
        }
        return sb.toString();
    }

    public static String exChange(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    public static boolean checkStrIs16(String str) {
        String regStr = "[a-f0-9A-F]{" + str.length() + "}";
        if (str.matches(regStr)) {
            return true;

        } else {

            return false;
        }
    }


    public static int StartInventory() throws radioBusyException {
        int value = -1;
        try {

            if (myRadio.StartInventory(0, 200) == ctrlOperateResult.OK) {

                value = 1;
            } else {

                value = 0;
            }
        } catch (radioBusyException e) {
            e.printStackTrace();
            value = -1;
        }
        return value;
    }


    public static int StopInventory() {
        if (myRadio.StopInventory() == ctrlOperateResult.OK) {

            return 1;
        } else {

            return 0;
        }
    }


}
