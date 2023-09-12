import { useCatProduit } from "../../hooks/useCatProduit";
import { CatProduitContext } from "./CatProduitContext";

export const CatProduitProvider = ({ children }) => {
    
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
    } = useCatProduit();
    
    return(
        <CatProduitContext.Provider value={ 
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
                
            }
        }>
            {children}
        </CatProduitContext.Provider>
    );
}