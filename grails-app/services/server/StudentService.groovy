package server

import grails.gorm.services.Service

@Service(Student)
interface StudentService {

    Student get(Serializable id)

    List<Student> list(Map args)

    Long count()

    Student delete(Serializable id)

    Student save(Student student)

}
