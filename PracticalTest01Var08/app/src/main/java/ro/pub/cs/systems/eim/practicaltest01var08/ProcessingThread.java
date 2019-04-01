package ro.pub.cs.systems.eim.practicaltest01var08;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private String starText = null;
    private String str = null;

    private Random random = new Random();

    public ProcessingThread(Context context, String str) {
        this.context = context;
        this.str = str;
        /*String starText1 = new String(new char[str.length()]).replace('\0', '*');
        char[] starText2 = new char[str.length()];
        starText1.getChars(0, starText1.length(), starText2, 0);
        char[] str2 = new char[str.length()];
        str.getChars(0, str.length(), str2, 0);
        starText2[3] = str2[3];
        this.starText = new String(starText2);*/
        Log.d("[ProcessingThread]", "Service has started!");
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.putExtra("message", starText);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}

