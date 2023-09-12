import { useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { AuthContext } from "../auth/context/AuthContext";
import { CatProduitContext } from "../context/catproduit/CatProduitContext";
import { CatProduitModalForm } from "../components/catproduit/CatProduitModalForm";
import { CatProduitList } from "../components/catproduit/CatProduitList";
import { Paginator } from "../components/Paginator";

export const CatProduitPage = () => {
    const { page } = useParams();
    const { login } = useContext(AuthContext);
    console.log("page: "+page);

    const {
        registres,
        paginator,
        getRegistres,
        visibleForm,
        handlerFormOpen,
    } = useContext(CatProduitContext);
    


    useEffect(()=>{
        getRegistres(page);
      },[,page])

    
    return(<>
        { !visibleForm || 
        <CatProduitModalForm />
    }

        <div className="container my-4 border p-4">
            <h3>Catègories Produits</h3><hr />
                <div className="d-grid gap-2 d-md-flex justify-content-md-end">

                { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Catègorie Produit</button>}

                
            </div>
            <hr />
            {             
                    (registres?.length === 0)
                    ? <div className="alert alert-warning">Il n'y a pas catègories Produits enregistré...</div> 
                    : 
                    <>
                    <CatProduitList />
                    <Paginator url="/catproduits/page" paginator={paginator} />
                    </>
                }
    </div>


    </>);
}