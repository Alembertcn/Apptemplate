package com.king.template.module_option.bean

data class OptionExpirationEntry(
val expirationList:List<ExpirationItem>,
val recentExpiration:String,
val recentOptionChain:List<OptionTableEntry>
)