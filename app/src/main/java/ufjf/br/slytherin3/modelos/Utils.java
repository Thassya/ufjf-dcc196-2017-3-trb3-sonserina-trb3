package ufjf.br.slytherin3.modelos;

/**
 * Created by thassya on 05/12/17.
 */

public enum Utils {

    Utils() {
    };

    public enum Dificuldade{
        FACIL("Fácil", 1),
        QUASEFACIL("Quase Fácil", 2),
        MEDIO("Medio", 3),
        DIFICIL("Dificil", 4),
        MUITO_DIFICIL("Muito Dificil", 5);

        private String stringValue;
        public String getValue(){
            return stringValue;
        }
        public static String getEnumByString(String code){
            for(Dificuldade d : Dificuldade.values()){
                if(code == d.stringValue)
                    return d.name();
            }
            return null;
        }
        public static Dificuldade getByName(String name){
            for(Dificuldade prop : values()){
                if(prop.getValue().equals(name)){
                    return prop;
                }
            }

            throw new IllegalArgumentException(name + " is not a valid PropName");
        }
        private int intValue;
        private Dificuldade(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }


    public enum Estados{
        AFAZER("A Fazer", 1),
        EMEXECUCAO("Em Execução", 2),
        BLOQUEADA("Bloqueada", 3),
        CONLCUIDA("Concluída", 4);

        private String stringValue;
        public String getValue(){
            return stringValue;
        }
        public static String getEnumByString(String code){
            for(Estados e : Estados.values()){
                if(code == e.stringValue)
                    return e.name();
            }
            return null;
        }
        public static Estados getByName(String name){
            for(Estados prop : values()){
                if(prop.getValue().equals(name)){
                    return prop;
                }
            }

            throw new IllegalArgumentException(name + " is not a valid PropName");
        }
        private int intValue;
        private Estados(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }
}
