
import { useCommande } from "../../hooks/useCommande";
import { CommandeContext } from "./CommandeContext";

export const CommandeProvider = ({ children }) => {
    const {

        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistres,
        handlerFormOpen,
        handerFormClose,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        paginator,
        errors,
        initTypePaiment,

    } = useCommande();
    return(
        <CommandeContext.Provider value={ 
            {

                handlerAddRegistre,
                handlerSelectRegistre,
                getRegistres,
                handlerFormOpen,
                handerFormClose,
                visibleForm,
                initListe,
                selectRegistre,
                iniPaginator,
                initEstructure,
                initErrors,
                registres,
                paginator,
                errors,
                initTypePaiment,
            }
        }>
            {children}
        </CommandeContext.Provider>
    );

}
