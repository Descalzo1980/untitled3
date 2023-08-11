class SMSService: MessageService() {
    override fun sendMessage(sender : String , receiver : String , message : String) {
        if (sender.length == 5)
        super.sendMessage(sender , receiver , message)
    }
}