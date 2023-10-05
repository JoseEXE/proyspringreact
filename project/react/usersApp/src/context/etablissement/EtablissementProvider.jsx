import { useEtablissement } from "../../hooks/useEtablissement";
import { EtablissementContext } from "./EtablissementContext";

export const EtablissementProvider = ({ children }) => {
    const {
        handlerAddEtablissement,
        handlerSelectEtablissement,
        getEtablissements,
        handlerFormOpen,
        handerFormClose,
        optionForm,
        visibleForm,
        listeEtabliss,
        selectEtablissement,
        iniPaginator,
        etablissEst,
        initErrors,
        etablissements,
        paginator,
        errors,
    } = useEtablissement();
    
    return(
        <EtablissementContext.Provider value={ 
            {
                handlerAddEtablissement,
                handlerSelectEtablissement,
                getEtablissements,
                handlerFormOpen,
                handerFormClose,
                optionForm,
                visibleForm,
                listeEtabliss,
                selectEtablissement,
                iniPaginator,
                etablissEst,
                initErrors,
                etablissements,
                paginator,
                errors,
            }
        }>
            {children}
        </EtablissementContext.Provider>
    );
}