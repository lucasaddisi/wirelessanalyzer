package validator;

import pipeAndFilter.Processable;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class FilterCompabilityValidator {


    public FilterCompabilityValidator() {

    }

    public boolean validateCompability(Processable filterOutput, Processable filterInput) {

        //Ver de que tipo es el Filter output
            //si es de tipo SimplFilterImpl -> Si input es generator -> Error.  Si es Filter -> comparar el output del filterOutput con el Input del filterInput.
            //si es de tipo SinkImpl -> Error
            //Si es de tipo Generator Impl -> Si es generator, error. Si es Filter ->

        Type output = outputDataType(filterOutput);
        Type input = inputDataType(filterInput);

        System.out.println("El output es: " + output.getTypeName() + " y el input es: " + input.getTypeName());

        return output.equals(input);
    }

    public Type outputDataType(Processable filterOutput){
        List<Parameter> parameters = getParameters(filterOutput);

        /*TODO El criterio de retorno del tipo de parámetro es muy aleatorio. Que pasa si
        el útlimo parámetro no es el output del filter?? Actualmente se está devolviendo el
         último tipo de parámetro de la lista del constructor*/
        return parameters.get(parameters.size() -1).getParameterizedType();
    }

    public Type inputDataType(Processable filterInput){
        List<Parameter> parameters = getParameters(filterInput);

        return parameters.get(0).getParameterizedType();
    }

    private List<Parameter> getParameters(Processable filter) {
        Class c = filter.getClass();

        //Obtengo los constructores de la clase c
        List<Constructor> constructors = Arrays.asList(c.getDeclaredConstructors());
        //Obtengo los parámetros del constructor
        //TODO Qué pasa si el filtro el día de mañana tiene más de un constructor??
        return Arrays.asList(constructors.get(0).getParameters());
    }

}