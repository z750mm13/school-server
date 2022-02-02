package server

class Student {
    int id;
    String name;
    String lastName;
    String email;
    int semester;
    String enrollment;
    String specialty;

    static constraints = {
        id(unique:true, nullable:false, blank:false)
        name(nullable:false, blank:false)
        lastname(nullable:false, blank:false)
        email(unique:true, nullable:false, blank:false,email:true)
        semester(nullable:false, blank:false)
        enrollment inList: ['Licenciaturas','Maestrias','Doctorados']
        specialty inList: ['Enfermeria','Software','Arquitectura','Fiscal','Educacion','Comunicacion','Gastronomia']
    }

    static mapping = {
        table 'students'
        id column: 'id'
        name column:'name'
        lastname column:  'lastname'
        email column: 'email'
        semester column: 'semester'
        enrollment column: 'enrollment'
        specialty column: 'specialty'
    }
}