package jw.kingdom.hall.kingdomtimer.recorder.utils.wav;

import jw.kingdom.hall.kingdomtimer.recorder.entity.pcm.PcmData;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
//http://www-mmsp.ece.mcgill.ca/Documents/AudioFormats/WAVE/WAVE.html
//http://www.onicos.com/staff/iz/formats/wav.html
//http://www.neurophys.wisc.edu/auditory/riff-format.txt
public class WavDataSaver {

    public static void savePCM(OutputStream os, PcmData data, int srate, int channel, int format) throws IOException {
        byte[] header;
        if(!isFloat(format)) {
            header = new byte[44];
        } else {
            header = new byte[58];
        }

        long totalDataLen = data.getSize() + header.length -8;
        long bitrate = srate * channel * format;
        int blockAlign = ((channel * format) / 8);

        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        if(!isFloat(format)) {
            header[16] = (byte) 16;
        } else {
            header[16] = (byte) 18;
        }
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        if(!isFloat(format)) {
            header[20] = 0x0001; //PCM
        } else {
            header[20] = 0x0003;//IEEE float
        }
        header[21] = 0;
        header[22] = (byte) (channel & 0xFF);
        header[23] = (byte) ((channel >> 8) & 0xFF);
        header[24] = (byte) (srate & 0xff);
        header[25] = (byte) ((srate >> 8) & 0xff);
        header[26] = (byte) ((srate >> 16) & 0xff);
        header[27] = (byte) ((srate >> 24) & 0xff);
        header[28] = (byte) ((bitrate / 8) & 0xff);
        header[29] = (byte) (((bitrate / 8) >> 8) & 0xff);
        header[30] = (byte) (((bitrate / 8) >> 16) & 0xff);
        header[31] = (byte) (((bitrate / 8) >> 24) & 0xff);
        header[32] = (byte) (blockAlign & 0xFF); //block align
        header[33] = (byte) ((blockAlign >> 8) & 0xFF);
        header[34] = (byte) (format & 0xFF);
        header[35] = (byte) ((format >> 8) & 0xFF);
        if(!isFloat(format)) {
            header[36] = 'd';
            header[37] = 'a';
            header[38] = 't';
            header[39] = 'a';
            header[40] = (byte) (data.getSize()  & 0xff);
            header[41] = (byte) ((data.getSize() >> 8) & 0xff);
            header[42] = (byte) ((data.getSize() >> 16) & 0xff);
            header[43] = (byte) ((data.getSize() >> 24) & 0xff);
        } else {
            int numberOfSampleFrames = (int) (data.getSize()/blockAlign);

            header[36] = 0;
            header[37] = 0;
            header[38] = 'f';
            header[39] = 'a';
            header[40] = 'c';
            header[41] = 't';
            header[42] = 4;
            header[43] = 0;
            header[44] = 0;
            header[45] = 0;
            header[46] = (byte) (numberOfSampleFrames & 0xff);
            header[47] = (byte) ((numberOfSampleFrames >> 8) & 0xff);
            header[48] = (byte) ((numberOfSampleFrames >> 16) & 0xff);
            header[49] = (byte) ((numberOfSampleFrames >> 24) & 0xff);
            header[50] = 'd';
            header[51] = 'a';
            header[52] = 't';
            header[53] = 'a';
            header[54] = (byte) (data.getSize()  & 0xff);
            header[55] = (byte) ((data.getSize() >> 8) & 0xff);
            header[56] = (byte) ((data.getSize() >> 16) & 0xff);
            header[57] = (byte) ((data.getSize() >> 24) & 0xff);
        }

        os.write(header, 0, header.length);
        data.appendTo(os);
        os.close();
    }

    private static boolean isFloat(int format) {
        return (format/8)>3;
    }
}
