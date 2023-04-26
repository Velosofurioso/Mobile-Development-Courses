package com.lvb.courses.app_organizze.util

import android.util.Base64

class Base64Util {

    companion object {

        fun encodeBase64(text: String?): String {
            return Base64.encodeToString(text?.toByteArray(), Base64.DEFAULT).replace(Regex("(\\n|\\r)"), "")
        }

        fun decodeBase64(textEncoded: String): String {
            return String(Base64.decode(textEncoded, Base64.DEFAULT))
        }

    }
}