import { useProduit } from "../../hooks/useProduit";
import { ProduitContext } from "./ProduitContext";

export const ProduitProvider = ({ children }) => {
    
    const {
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistres,
        getCatProd,
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
        registresCatProd,
        paginator,
        errors,
        listTypePlat,
    } = useProduit();
    
    return(
        <ProduitContext.Provider value={ 
            {
                handlerAddRegistre,
                handlerSelectRegistre,
                getRegistres,
                getCatProd,
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
                registresCatProd,
                paginator,
                errors,
                listTypePlat,
                
            }
        }>
            {children}
        </ProduitContext.Provider>
    );
}