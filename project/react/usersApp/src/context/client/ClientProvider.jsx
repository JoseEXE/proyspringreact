import { useClient } from "../../hooks/useClient";
import { ClientContext } from "./ClientContext";


export const ClientProvider = ({ children }) => {
    
    const {
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistres,
        handlerFormOpen,
        handerFormClose,
        optionForm,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        paginator,
        errors,
        handlerRecherche,
        registresRecherche,
        handlerSelectClientAdresse,
        VisibleregistresAdresseClient,

    } = useClient();
    
    return(
        <ClientContext.Provider value={
            {
                handlerAddRegistre,
                handlerSelectRegistre,
                getRegistres,
                handlerFormOpen,
                handerFormClose,
                optionForm,
                visibleForm,
                initListe,
                selectRegistre,
                iniPaginator,
                initEstructure,
                initErrors,
                registres,
                paginator,
                errors,
                handlerRecherche,
                registresRecherche,
                handlerSelectClientAdresse,
                VisibleregistresAdresseClient,
            }
        }>
                 {children}
        </ClientContext.Provider>



    );
}