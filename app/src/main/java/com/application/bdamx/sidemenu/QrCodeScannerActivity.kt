package com.application.bdamx.sidemenu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.application.bdamx.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_qr_code_scanner.*
import java.io.IOException

class QrCodeScannerActivity : AppCompatActivity() {
    var barcodeDetector: BarcodeDetector?=null
    var cameraSource: CameraSource? = null
    var cameraid = 0
    private val CAMERA = 0x5
    private val TAG = QrCodeScannerActivity::class.java.getSimpleName()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_scanner)
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                askForPermission(Manifest.permission.CAMERA, CAMERA)
                //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
                // requestPermission();
            }

        }

        cameraView.setZOrderMediaOverlay(true)

        //  barcodeInfo = (TextView) findViewById(R.id.bar_code);
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE)
            .build()
        if (barcodeDetector!!.isOperational()) {
            // Toast.makeText(this, "Sorry couldn't setup the dectector", Toast.LENGTH_LONG).show();
        }
        if (cameraSource != null) {
            cameraSource!!.release()
            cameraSource = null
        } else {
            Log.i("scannn==", "Initi scabn camme====")

            cameraSource = CameraSource.Builder(this@QrCodeScannerActivity, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920, 1024)
                .build()

        }
        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {


                if (ActivityCompat.checkSelfPermission(this@QrCodeScannerActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    /*ActivityCompat.requestPermissions(ScanAcitivty.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
                        cameraSource.start(cameraView.getHolder());*/
                    Log.i("scannn==", "permissssion not granddeed")

                    return
                }
                Log.i("scannn==", "permissssion  granddeed")

                try {
                    cameraSource!!.start(cameraView.holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                if (cameraSource != null) {
                    cameraSource!!.release()
                    cameraSource = null
                }
            }
        })
        barcodeDetector!!.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                //   Frame frame = new Frame.Builder().setBitmap(bitmap).build();

                val barcodes = detections.detectedItems
                println("111111======="+barcodes.valueAt(0).displayValue)
                Log.e(TAG, "type===" )


                if (barcodes.size() != 0) {
                    val intent = Intent()
                    intent.putExtra("barcode", barcodes.valueAt(0))
                    setResult(RESULT_OK, intent)
                    finish()


                }
            }
        })

        frontback_camera.setOnClickListener(View.OnClickListener {
            if (cameraSource != null) {
                cameraSource!!.release()
                cameraSource = null
            }
            if (cameraid == CameraSource.CAMERA_FACING_BACK) {
                cameraid = CameraSource.CAMERA_FACING_FRONT
                cameraSource = CameraSource.Builder(this@QrCodeScannerActivity, barcodeDetector)
                    .setFacing(cameraid)
                    .setRequestedFps(24f)
                    .setAutoFocusEnabled(true)
                    .setRequestedPreviewSize(1920, 1024)
                    .build()
            } else if (cameraid == CameraSource.CAMERA_FACING_FRONT) {
                cameraid = CameraSource.CAMERA_FACING_BACK
                cameraSource = CameraSource.Builder(this@QrCodeScannerActivity, barcodeDetector)
                    .setFacing(cameraid)
                    .setRequestedFps(24f)
                    .setAutoFocusEnabled(true)
                    .setRequestedPreviewSize(1920, 1024)
                    .build()
            }


            try {

                if (ActivityCompat.checkSelfPermission(this@QrCodeScannerActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return@OnClickListener
                }
                cameraSource!!.start(cameraView.holder)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })


    }

    private fun askForPermission(permission: String, requestCode: Int?) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode!!)

            } else {

                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode!!)
            }

        } else {

            // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show()
        }
    }

}