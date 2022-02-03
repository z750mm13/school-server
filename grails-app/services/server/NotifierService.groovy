package server

class NotifierService {

    boolean transactional = false

    def mailService

    def sendAccount(email, password) {
        mailService.sendMail{
            to email
            from "School"
            subject "New Account"
            body "Hi ${password} this is your pass from a school!"
        }
    }
}