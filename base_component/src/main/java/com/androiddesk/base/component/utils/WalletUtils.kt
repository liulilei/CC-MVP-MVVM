package io.newdex.exchange.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.newdex.exchange.R
import io.newdex.exchange.constants.Constants
import io.newdex.exchange.module.asset.m.SimpleWalletLogin
import io.newdex.exchange.module.asset.m.SimpleWalletPay
import io.newdex.exchange.net.NetApi
import java.net.URLEncoder


/**
 *@Description:
 * @author: lll
 * @date: 2018/9/6
 */
class WalletUtils {

    companion object {
//        fun getAppInfo(): AppInfo {
//            var appInfo = AppInfo()
//            appInfo.name = BaseApplication.getInstance()?.resources?.getString(R.string.app_name)
//            appInfo.icon = "icon"
//            appInfo.dappCallbackScheme = "newdex"
//            appInfo.dappRedirectURL = "https://newdex.io"
//            appInfo.description = "xvxvxvxv"
//            appInfo.version = AppUtils.getAppVersionName(BaseApplication.getInstance()!!)
//            return appInfo
//        }

//        fun requestAuthorize(context: Context, meetOneCallBack: MeetOneCallBack) {
//            val authorize = Authorize()
//            authorize.description = "get account info "
//            MeetOneManager.getInstance().requestAuthorize(context, authorize, getAppInfo(), meetOneCallBack)
//        }
//
//        fun requestSignature(context: Context, accountNo: String, data: String, meetOneCallBack: MeetOneCallBack) {
//            LogUtils.e("data11111111111", data)
//            val signature = Signature()
//            signature.accountName = accountNo
//            signature.data = data
//            signature.description = "thisademo"
//            MeetOneManager.getInstance().requestSignature(context, signature, getAppInfo(), meetOneCallBack)
//        }

//        fun transfer(context: Context, amount: Double, account: String, tokenName: String, tokenContract: String,
//                     tokenPrecision: Int, memo: String, meetOneCallBack: MeetOneCallBack) {
//            val transfer = Transfer()
//            transfer.amount = amount.toString()
//            transfer.description = "transfer"
//            transfer.from = account//from account name
//            transfer.to = Constants.NEWDEX_POCKET_ACCOUNT//to account name
//            transfer.tokenName = tokenName
//            transfer.tokenContract = tokenContract
//            transfer.tokenPrecision = tokenPrecision//set token Precision
//            transfer.memo = memo
//            MeetOneManager.getInstance().requestTransfer(context, transfer, getAppInfo(), meetOneCallBack)
//        }

        fun simpleWalletLogin(context: Context, uuId: String) {
            var simpleWalletLogin = SimpleWalletLogin(Constants.PROTOCOL, "1.0", context.getString(R.string.app_name),
                    "https://ndx.340wan.com/static/logo-white-bg.png", "login", uuId, NetApi.WALLET_VERIFY,
                    /*"The first EOS based decentralized exchange in the world"*/ "", Constants.LOGIN_CALL_BACK)
            val uriBuild = StringBuilder()
            uriBuild.append("simplewallet://eos.io")
            uriBuild.append("?")
            try {
                val signatureDecode = URLEncoder.encode(ParseDataUtils.toJson(simpleWalletLogin), "utf-8")
                uriBuild.append("param").append("=").append(signatureDecode)
                val uriStr = uriBuild.toString()
                LogUtils.e("simpleWallet", "uri===> $uriStr")
                val uri = Uri.parse(uriStr)
                if (uri != null) {
                    val intent = Intent("android.intent.action.VIEW", uri)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            } catch (var9: Exception) {
                var9.printStackTrace()
            }
        }

        fun simpleWalletPay(context: Context, amount: Double, account: String, tokenName: String, tokenContract: String,
                            tokenPrecision: Int, memo: String) {
            var simpleWalletPay = SimpleWalletPay(Constants.PROTOCOL, "1.0", context.getString(R.string.app_name),
                    "https://ndx.340wan.com/static/logo-white-bg.png", "transfer", account, Constants.NEWDEX_POCKET_ACCOUNT,
                    amount.toString(), tokenContract, tokenName, tokenPrecision, memo,
                    /*"The first EOS based decentralized exchange in the world"*/"", Constants.PAY_CALL_BACK)
            val uriBuild = StringBuilder()
            uriBuild.append("simplewallet://eos.io")
            uriBuild.append("?")
            try {
                val signatureDecode = URLEncoder.encode(ParseDataUtils.toJson(simpleWalletPay), "utf-8")
                uriBuild.append("param").append("=").append(signatureDecode)
                val uriStr = uriBuild.toString()
                LogUtils.e("simpleWallet", "uri===> $uriStr")
                val uri = Uri.parse(uriStr)
                if (uri != null) {
                    val intent = Intent("android.intent.action.VIEW", uri)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            } catch (var9: Exception) {
                var9.printStackTrace()
            }
        }

    }


}