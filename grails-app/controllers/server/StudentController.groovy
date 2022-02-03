package server

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class StudentController {

    StudentService studentService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentService.list(params), model:[studentCount: studentService.count()]
    }

    def show(Long id) {
        respond studentService.get(id)
    }
    @Transactional
    def save(Student student) {
        def pass_length = 15;
        def special = ['~' ,'`'] // you get the idea...
        def pool = ['a'..'z','A'..'Z',0..9,'_'].flatten().plus(special);
        Random rand = new Random(System.currentTimeMillis());
        def passChars = (0..pass_length - 1).collect { pool[rand.nextInt(pool.size)] };
        def specialChar = special[rand.nextInt(special.size)]
        passChars[rand.nextInt(passChars.size)] = specialChar
        def PASSWORD = passChars.join();

        student.setpassword(PASSWORD)

        if (student == null) {
            render status: NOT_FOUND
            return
        }
        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            response.setContentType("application/json")
            render '{"complete":false,"message":"Estudiante creado anteriormente"}'
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors
            return
        }

        response.setContentType("application/json")
        render '{"complete":true,"message":"Estudiante creado correctamente"}'
    }

    @Transactional
    def update(Student student) {
        if (student == null) {
            render status: NOT_FOUND
            return
        }
        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond student.errors
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors
            return
        }

        respond student, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || studentService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
