package server

class Student {
    int id
    String name
    String lastname
    String email
    String password
    int semester
    String enrollment
    String specialty

    static constraints = {
        id(unique:true, nullable:false, blank:false)
        name(nullable:false, blank:false)
        lastname(nullable:false, blank:false)
        email(unique:true, nullable:false, blank:false,email:true)
        password(nullable:true, blank:false )
        semester(nullable:false, min:1, blank:false)
        enrollment(unique:true, size: 9..9, nullable:false, blank:false)
        specialty inList: ['Enfermeria','Software','Arquitectura','Fiscal','Educacion','Comunicacion','Gastronomia']
    }

    static mapping = {
        table 'students'
        id column: 'id'
        name column:'name'
        lastname column:  'lastname'
        email column: 'email'
        password column: 'password'
        semester column: 'semester'
        enrollment column: 'enrollment'
        specialty column: 'specialty'
    }

    String getemail(){
        return email;
    }

    void setpassword(String pass) {
        password = pass;
    }
}