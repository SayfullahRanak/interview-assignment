package com.assignment.utils.tools

//import com.assignment.utils.listener.SingleOptionResponseListener
import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.provider.Settings
import android.util.DisplayMetrics
import androidx.core.content.pm.PackageInfoCompat
import com.assignment.utils.R
import com.tapadoo.alerter.Alerter
import java.net.NetworkInterface
import java.util.*


class Utils {
    companion object {
        fun appVersionName(ctx: Context): String {
            return try {
                val pInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                val version = pInfo.versionName
                version;
            } catch (e: Exception) {
                e.printStackTrace()
                "000"
            }
        }

        fun appVersion(ctx: Context): Long {
            val pInfo: PackageInfo =
                ctx.packageManager.getPackageInfo(ctx.packageName, 0)
            return PackageInfoCompat.getLongVersionCode(pInfo)
        }

        fun openURL(mActivity: Activity?, url: String?) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                mActivity?.startActivity(intent)
            } catch (e: Exception) {
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun unixTimeToDate(unixTime: Long, outPutFormat: String = "yyyy-MM-dd"): String {
            val sdf = java.text.SimpleDateFormat(outPutFormat)
            val date = java.util.Date(unixTime)
            return sdf.format(date)
        }


        fun openInstagramUser(activity: Activity, IGUserName: String) {
            val uri = Uri.parse("http://instagram.com/_u/$IGUserName")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                activity.startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/$IGUserName")
                    )
                )
            }
        }

        fun checkInternetConnection(context: Context): Boolean {
            val connectivity: ConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity == null) {
                return false
            } else {
                val info: Array<NetworkInfo> = connectivity.getAllNetworkInfo()
                if (info != null) {
                    for (anInfo in info) {
                        if (anInfo.getState() === NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        fun openWhatsApp(activity: Activity, number: String) {
            val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            activity.startActivity(i)
        }

        fun openFaceBookUser(activity: Activity, fbUser: String) {
            val uri = Uri.parse("fb://page/$fbUser")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.facebook.katana")
            try {
                activity.startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/$fbUser")
                    )
                )
            }

        }

        fun openDialer(activity: Activity, number: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            activity.startActivity(intent)
        }

        fun openEmailSender(
            activity: Activity,
            receiver: String,
            subject: String? = "",
            bodyText: String? = ""
        ) {
            val mailto = "mailto:$receiver?" +
                    "&subject=" + Uri.encode(subject) +
                    "&body=" + Uri.encode(bodyText)

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                activity.startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
            }

        }

        @SuppressLint("HardwareIds")
        fun getSystemUniqueCode(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun getSerialNo() : String{
            return "000000"
        }


        fun getMacAddress(): String? {
            try {
                val all: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (nif in all) {
                    if (!nif.name.equals("wlan0", ignoreCase = true)) continue
                    val macBytes = nif.hardwareAddress ?: return ""
                    val res1 = StringBuilder()
                    for (b in macBytes) {
                        res1.append(String.format("%02X:", b))
                    }
                    if (res1.length > 0) {
                        res1.deleteCharAt(res1.length - 1)
                    }
                    return res1.toString()
                }
            } catch (ex: java.lang.Exception) {
            }
            return "02:00:00:00:00:00"
        }


        fun setClipboard(context: Context, text: String) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)
        }

        fun isPackageInstalled(
            packageName: String,
            context: Context?
        ): Boolean {
            if (context == null) return false
            val pm: PackageManager = context.packageManager
            return try {
                pm.getPackageInfo(packageName, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        fun openGooglePlayAppByPKG(mActivity: Activity?, pkg: String) {

            try {
                mActivity?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$pkg")
                    )
                )
            } catch (error: ActivityNotFoundException) {
                mActivity?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$pkg")
                    )
                )
            }
        }

        fun showAlert(
            mActivity: Activity?,
            title: String? = "",
            msg: String?,
            isError: Boolean = true
        ) {
            if (mActivity != null)
                Alerter.create(mActivity)
                    .setTitle(title ?: (if (isError) "Error" else ""))
                    .setText(msg ?: "")
                    .setDuration(8000)
                    .setBackgroundColorRes((if (isError) R.color.orange else R.color.redError))
                    .show()
        }

//        fun showPlainDialogBoxWithSingleOption(
//            activity: Activity,
//            titleText: String?,
//            descriptionText: String?,
//            iconImage: Drawable?,
//            canDismiss: Boolean,
//            responseListener: SingleOptionResponseListener
//        ): AlertDialog? {
//            val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
//            val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
//            val dialogView: View = LayoutInflater.from(activity)
//                .inflate(R.layout.custom_dialog_message_option, viewGroup, false)
//            val buttonPositive: AppCompatButton = dialogView.findViewById(R.id.dialogOk)
//            val buttonNegative: AppCompatButton = dialogView.findViewById(R.id.dialogCancel)
//            buttonNegative.visibility = View.GONE
//            val title = dialogView.findViewById<View>(R.id.title) as TextView
//            title.text = titleText
//            val description = dialogView.findViewById<View>(R.id.description) as TextView
//            description.text = descriptionText
//            val icon = dialogView.findViewById<View>(R.id.dIcon) as AppCompatImageView
//            icon.setImageDrawable(iconImage)
//            builder.setView(dialogView)
//            val alertDialog = builder.create()
//            buttonPositive.setOnClickListener { responseListener.positive(alertDialog) }
//            if (!canDismiss) alertDialog.setCanceledOnTouchOutside(false)
//            //        alertDialog.show();
//            return alertDialog
//        }



        fun vasIconURL(productDesc: String?): String {
            return "https://www.m1payall.com/images/ewallet/media/logos/${productDesc?.toLowerCase()
                ?.replace(
                    " ",
                    "_"
                )?.replace("`", "")
            }.png"
        }
    }
}