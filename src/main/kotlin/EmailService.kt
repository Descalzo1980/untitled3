class EmailService: MessageService() {
    override fun sendMessage(sender : String , receiver : String , message : String) {
        if (sender.contains("@"))
        super.sendMessage(sender , receiver , message)
    }
}