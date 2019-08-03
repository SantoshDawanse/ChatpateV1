package com.dawanse.chatpatev1;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dawanse.chatpatev1.variables.MotorVariables;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class RegularChatpate extends AppCompatActivity {

    private final String DEVICE_ADDRESS="98:D3:31:FD:8B:66";
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    boolean deviceConnected=false;
    Thread thread;
    byte buffer[];
    int bufferPosition;
    boolean stopThread;

    private LinearLayout regularChatpateBtn;
    private TextView textView;
    private Button bluetoothBtn, stopMachineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_chatpate);

        regularChatpateBtn = findViewById(R.id.regular_chatpate_btn);
        textView = findViewById(R.id.regular_textview);
        bluetoothBtn = findViewById(R.id.regular_bluetooth_open);
        stopMachineBtn = findViewById(R.id.regular_bluetooth_close);

        setUiEnabled(false);

        regularChatpateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                processRegularChatpate();

            }
        });
    }

    public void setUiEnabled(boolean bool)
    {
        bluetoothBtn.setEnabled(!bool);
        stopMachineBtn.setEnabled(bool);
        regularChatpateBtn.setEnabled(bool);
        textView.setEnabled(bool);

    }

    public boolean BTinit()
    {
        boolean found=false;
        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Pair the Device first",Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device=iterator;
                    found=true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect()
    {
        boolean connected=true;
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            connected=false;
        }
        if(connected)
        {
            try {
                outputStream=socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream=socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return connected;
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];
        Thread thread  = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopThread)
                {
                    try
                    {
                        int byteCount = inputStream.available();
                        if(byteCount > 0)
                        {
                            byte[] rawBytes = new byte[byteCount];
                            inputStream.read(rawBytes);
                            final String string=new String(rawBytes,"UTF-8");
                            handler.post(new Runnable() {
                                public void run()
                                {
                                    textView.append(string);
                                }
                            });

                        }
                    }
                    catch (IOException ex)
                    {
                        stopThread = true;
                    }
                }
            }
        });

        thread.start();
    }

    public void onClickBluetooth(View view) {
        if(BTinit())
        {
            if(BTconnect())
            {
                setUiEnabled(true);
                deviceConnected=true;
                beginListenForData();
                textView.append("\nConnection Opened!\n");
            }

        }
    }

    public void processRegularChatpate() {
        String[] itemvalue = new String[] {
                MotorVariables.PWM_CHANA, MotorVariables.ACW_CHANA, MotorVariables.REV_CHANA,
                MotorVariables.PWM_CHAUCHAU, MotorVariables.ACW_CHAUCHAU, MotorVariables.REV_CHAUCHAU,
                MotorVariables.PWM_CHILLY, MotorVariables.ACW_CHILLY, MotorVariables.REV_CHILLY,
                MotorVariables.PWM_CUCUMBER, MotorVariables.ACW_CUCUMBER, MotorVariables.REV_CUCUMBER,
                MotorVariables.PWM_KAGATI, MotorVariables.ACW_KAGATI, MotorVariables.TIME_KAGATI,
                MotorVariables.PWM_MURAI, MotorVariables.ACW_MURAI, MotorVariables.REV_MURAI,
                MotorVariables.PWM_OIL, MotorVariables.ACW_OIL, MotorVariables.TIME_OIL,
                MotorVariables.PWM_ONION, MotorVariables.ACW_ONION, MotorVariables.REV_ONION,
                MotorVariables.PWM_POTATO, MotorVariables.ACW_POTATO, MotorVariables.REV_POTATO,
                MotorVariables.PWM_TOMATO, MotorVariables.ACW_TOMATO, MotorVariables.REV_TOMATO
        };
//        String string = editText.getText().toString();
        String string = "hello";
        string.concat("\n");
        try {
            byte[] data = itemvalue.toString().getBytes();

//            outputStream.write(string.getBytes());
//            textView.append("\nSent Data:" + string + "\n");
            for (int i = 0; i < itemvalue.length; i++) {
                outputStream.write(itemvalue[i].getBytes());
                textView.append("\nSent Data:" + itemvalue[i] + "\n");
            }
//            outputStream.write(itemvalue.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        textView.append("\nSent Data:" + itemvalue.toString() + "\n");

    }

    public void stopMachine(View view) throws IOException {
        stopThread = true;
        outputStream.close();
        inputStream.close();
        socket.close();
        setUiEnabled(false);
        deviceConnected=false;
        textView.append("\nConnection Closed!\n");
    }
}
