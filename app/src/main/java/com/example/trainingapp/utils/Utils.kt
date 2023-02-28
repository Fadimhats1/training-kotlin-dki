package com.example.trainingapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.VersionedPackage
import android.graphics.Bitmap
import android.util.Base64
import androidx.core.content.pm.PackageInfoCompat
import java.io.ByteArrayOutputStream
import java.util.Base64.getEncoder
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Utils {
    companion object{
        val TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5UZG1aak00WkRrM05qWTBZemM1TW1abU9EZ3dNVEUzTVdZd05ERTVNV1JsWkRnNE56YzRaQT09In0.eyJhdWQiOiJodHRwOlwvXC9vcmcud3NvMi5hcGltZ3RcL2dhdGV3YXkiLCJzdWIiOiJlcmljay5waW5lbmthQGNhcmJvbi5zdXBlciIsImFwcGxpY2F0aW9uIjp7Im93bmVyIjoiZXJpY2sucGluZW5rYSIsInRpZXJRdW90YVR5cGUiOiJyZXF1ZXN0Q291bnQiLCJ0aWVyIjoiVW5saW1pdGVkIiwibmFtZSI6Im1vYmlsZSIsImlkIjo0LCJ1dWlkIjpudWxsfSwic2NvcGUiOiJhbV9hcHBsaWNhdGlvbl9zY29wZSBkZWZhdWx0IiwiaXNzIjoiaHR0cHM6XC9cL2FwaW0tZGV2LmRhbmFraW5pLmNvLmlkOjQ0M1wvb2F1dGgyXC90b2tlbiIsInRpZXJJbmZvIjp7IlVubGltaXRlZCI6eyJ0aWVyUXVvdGFUeXBlIjoicmVxdWVzdENvdW50Iiwic3RvcE9uUXVvdGFSZWFjaCI6dHJ1ZSwic3Bpa2VBcnJlc3RMaW1pdCI6MCwic3Bpa2VBcnJlc3RVbml0IjpudWxsfX0sImtleXR5cGUiOiJQUk9EVUNUSU9OIiwic3Vic2NyaWJlZEFQSXMiOlt7InN1YnNjcmliZXJUZW5hbnREb21haW4iOiJjYXJib24uc3VwZXIiLCJuYW1lIjoibW9iaWxlIiwiY29udGV4dCI6IlwvbW9iaWxlXC92MSIsInB1Ymxpc2hlciI6ImVyaWNrLnBpbmVua2EiLCJ2ZXJzaW9uIjoidjEiLCJzdWJzY3JpcHRpb25UaWVyIjoiVW5saW1pdGVkIn1dLCJjb25zdW1lcktleSI6IkNsVkN6Vk9vX3UxZVlkWjFjWVdaZnNjRkF4c2EiLCJleHAiOjM3MzQyNTk3NjksImlhdCI6MTU4Njc3NjEyMiwianRpIjoiNDE3NGVhZDAtMDg0Zi00NjY5LTk0OTUtZGYxZjRlMWY5YTgzIn0.i-Wg8qlQ7yRkupFk2v2kNwx-iKk2dJYkL50tm0jskViP1nx-VgvDD5PPRqx0_KhSTXbke7AwrXPd4KsvRtQlQNSzpRXnIR96AdAnu6SY9jfDIqw6aavhhqpny-YdbcSBTQ6iU_Geq3x1W1fnM5pNLljcjqz33cXXtxjMcwqJTEILmH4FQ63JRzY3Cuo0jIkG-AXVQT7PWGKV6xNnPdHyTAZvBYtG5EhOQyO7XwQR8DaQzBgsBCJ1FqYhUJgwD4lGlh8-kIAKebnzmKHaXL5ewtm5h5R5dHC_bGO1aCU1QrhvDuFr7_UyaXG25AZlCn7bkw0ZYMUaEhAeMMW15NdyBw"

        fun getVersionName(applicationContext: Context, packageName: String): String{
            val packageManager = applicationContext.packageManager
            val packageName = packageName
            @Suppress("DEPRECATION")
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return PackageInfoCompat.getLongVersionCode(packageInfo).toString()
        }
        fun encodeImage(image: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
        @SuppressLint("NewApi")
        fun encrypt(str: String, secretKey: String, secIV: String): String {
            val encrypted =
                cipher(Cipher.ENCRYPT_MODE, secretKey, secIV).doFinal(str.toByteArray(Charsets.UTF_8))
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                return String(getEncoder().encode(encrypted))
            } else {
                return String(android.util.Base64.encode(encrypted, android.util.Base64.NO_WRAP))
            }
            return cipher(Cipher.ENCRYPT_MODE, secretKey,secIV).toString()
        }

        private fun cipher(opmode: Int, secretKey: String, secIV: String): Cipher {
            if (secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
            val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
            val iv = IvParameterSpec(secIV.toByteArray(Charsets.UTF_8))
            c.init(opmode, sk, iv)
            return c
        }

        fun getVersionName(packageManager: PackageManager, packageName: String): String {
            @Suppress("DEPRECATION")
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return PackageInfoCompat.getLongVersionCode(packageInfo).toString()
        }
    }
}