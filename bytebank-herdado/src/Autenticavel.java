//interface não tem nada concreto (como atributos e métodos) apenas métodos abstratos
//uma interface é um contrato Autenticavel
// quem assinar esse contrato precisa implementar
// o método setSenha, autentica

public abstract interface Autenticavel {

    public abstract void setSenha(int senha);

    public abstract boolean autentica(int senha);

}
