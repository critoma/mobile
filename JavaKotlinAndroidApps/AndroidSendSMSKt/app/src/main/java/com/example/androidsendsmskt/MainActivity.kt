package com.example.androidsendsmskt
/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

 */

import android.os.Bundle

import android.Manifest
import android.content.pm.PackageManager
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.content.ContextCompat
import android.app.Activity
//import android.content.Context
//import android.os.Build
//import android.support.annotation.RequiresApi
import android.telephony.SmsManager
import android.util.Base64
//import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


//import java.util.* //for Base64 from Java when Android API >= 27

//import java.security.*

// here is the Cipher class
import javax.crypto.*
import javax.crypto.spec.*
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
//import sun.jvm.hotspot.utilities.IntArray



class MainActivity : Activity() {
    var phoneNo:String = ""
    var message:String = ""
    private var buttonSend: Button? = null
    private var textPhoneNo:EditText? = null
    private var textSMS:EditText? = null
    private var allowedWithSms:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //setContentView(R.layout.activity_main)
        buttonSend = findViewById<Button>(R.id.buttonSend)
        textPhoneNo = findViewById<EditText>(R.id.editTextPhoneNo)
        textSMS = findViewById<EditText>(R.id.editTextSMS)
        buttonSend?.setOnClickListener(OnClickListener { sendSmsMessage() })
    }

    protected fun sendSmsMessage() {
        phoneNo = textPhoneNo?.getText().toString()
        message = textSMS?.getText().toString()
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED))
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                    arrayOf<String>(Manifest.permission.SEND_SMS),
                    MY_PERMISSIONS_REQUEST_SEND_SMS)
            }
        } else {
            if (allowedWithSms) {
                val password = "password#1234567"
                //val iv = "12345678"

                var c = CryptoJAES()
                var testText = message
                if ((testText.length % 16) != 0)
                    testText = testText.padEnd((testText.length + (16 - (testText.length % 16))), ' ')

                var encryptTextBytes = c.aesCryptoECB(testText.toByteArray(), password.toByteArray(), 0)

                val smsManager = SmsManager.getDefault() as SmsManager
                //smsManager.sendTextMessage(phoneNo, null, message, null, null)
                //smsManager.sendTextMessage(phoneNo, null, Base64.getEncoder().encodeToString(encryptTextBytes), null, null)
                val b64str = Base64.encodeToString(encryptTextBytes, Base64.DEFAULT)
                smsManager.sendTextMessage(phoneNo, null, b64str, null, null)

                ///val uri = Uri.parse("smsto:"+phoneNo)
                ///val sendIntent = Intent(Intent.ACTION_SENDTO, uri)
                ///sendIntent.putExtra("sms_body", "Here goes your message ...")
                //sendIntent.type = "vnd.android-dir/mms-sms"
                ///startActivity(sendIntent)

                Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    //@RequiresApi(Build.VERSION_CODES.CUR_DEVELOPMENT)
    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<String>, grantResults:IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_SEND_SMS -> {
                if ((grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                {
                    allowedWithSms = true
                    Toast.makeText(getApplicationContext(), "SMSs will be sent.",
                        Toast.LENGTH_LONG).show()
                }
                else
                {
                    allowedWithSms = false
                    Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again.", Toast.LENGTH_LONG).show()
                    return
                }
            }
        }
    }

    companion object {
        private val MY_PERMISSIONS_REQUEST_SEND_SMS = 0
    }
}

class CryptoJAES
{
    fun aesCryptoECB(inputdata: ByteArray, key: ByteArray, mode: Int): ByteArray?
    {
        try {
            val cipher = Cipher.getInstance("AES/ECB/NoPadding")
            val secretKeySpec = SecretKeySpec(key, "AES")

            if (mode == 0) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
            }

            return cipher.doFinal(inputdata)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun aesCryptoCBC(inputData: ByteArray, key: ByteArray, ivs: ByteArray, mode: Int): ByteArray?
    {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val secretKeySpec = SecretKeySpec(key, "AES")
            val finalIvs = ByteArray(16)

            val len = if (ivs.size > 16) 16 else ivs.size
            System.arraycopy(ivs, 0, finalIvs, 0, len)
            val ivps = IvParameterSpec(finalIvs)

            if (mode == 0) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivps)
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivps)
            }

            return cipher.doFinal(inputData)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
