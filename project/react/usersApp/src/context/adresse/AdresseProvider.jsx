import { useAdresse } from "../../hooks/useAdresse";
import { AdresseContext } from "./AdresseContext";

export const AdresseProvider = ({ children }) =>{
    const {
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistresAdresse,
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
        errors,
        handlerIdClientActive,
        clientActive,
        handlerSelectAdresse,
        adresseActive,

    } = useAdresse();

    return(<AdresseContext.Provider value={ 
        {
            handlerAddRegistre,
            handlerSelectRegistre,
            getRegistresAdresse,
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
            errors,
            handlerIdClientActive,
            clientActive,
            handlerSelectAdresse,
            adresseActive,
        }
    }>
        {children}
    </AdresseContext.Provider>);
}