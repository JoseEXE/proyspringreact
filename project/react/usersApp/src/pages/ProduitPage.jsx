import { useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { AuthContext } from "../auth/context/AuthContext";
import { ProduitContext } from "../context/produit/ProduitContext";
import { Paginator } from "../components/Paginator";
import { ProduitModalForm } from "../components/produit/ProduitModalForm";
import { ProduitList } from "../components/produit/ProduitList";

export const ProduitPage = () =>{
    const { page } = useParams();
    const { login } = useContext(AuthContext);
    console.log("page: "+page);

    const {
        registres,
        registresCatProd,
        paginator,
        getRegistres,
        getCatProd,

        visibleForm,
        handlerFormOpen,
    } = useContext(ProduitContext);
    

    useEffect(()=>{
        getRegistres(page);
        getCatProd();
      },[,page])

    
    return(<>
        { !visibleForm || 
        <ProduitModalForm />
    }

        <div className="container my-4 border p-4">
            <h3>Produits</h3><hr />
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">

                { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Produit</button>}

                
            </div>
            <hr />
            {             

                (registres?.length === 0)
                ? <div className="alert alert-warning">Il n'y a pas Produits enregistré...</div> 
                : 
                <>
                <ProduitList />
                <Paginator url="/catproduits/page" paginator={paginator} />
                </>
            }
    </div>


    </>);
}
