package com.application.bdamx.model

data class currencyModel(var Img: String,var currency: String,var pairname: String,var high: String,var low: String,var change: String,var marketPrice: String,var pair: String)
data class notificationModel(var desc: String,var date: String)
data class navigationModel(var id: Int, var img: Int, var name: String)
data class newsModel(var date: String, var title: String, var content: String)
data class datamarketModel(var pair: String, var price: String, var usdprice: String, var change: String, var vol: String)
data class walletModel(var currency: String, var blnc: String, var hold: String, var img: String, var min: String, var max: String, var fee: String)
data class bookModel(var date: String,var currency: String, var price: String, var amount: String, var total: String, var type: String)
data class openorderModel(var currency: String, var type: String, var date: String, var amount: String, var price: String, var total: String, var status: String)
data class orderModel(var currency: String, var type: String, var date: String, var amount: String, var price: String, var total: String, var status: String, var fee: String)
data class historyModel(var currency: String, var type: String, var date: String, var amount: String, var price: String, var total: String, var status: String, var fee: String)
data class paperkeyModel(var keyname: String)
data class tradepairModel(var maker: String,var taker: String,var marketPrice: String,var pair: String,var pairid: String,var fromid: String,var toid: String)