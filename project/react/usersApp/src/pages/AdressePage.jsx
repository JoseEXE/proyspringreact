import { useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { AuthContext } from "../auth/context/AuthContext";
import { AdresseContext } from "../context/adresse/AdresseContext";
import { AdresseModalForm } from "../components/adresse/AdresseModalForm";
import { AdresseList } from "../components/adresse/AdresseList";

export const AdressePage = ({ clientId }) =>{
    const { page } = useParams();
    const { login } = useContext(AuthContext);

    const {
        registres,
        getRegistresAdresse,
        visibleForm,
        handlerFormOpen,
        handlerRecherche,
        handlerIdClientActive,
        clientActive,
        

    } = useContext(AdresseContext);

    useEffect(()=>{
        handlerIdClientActive(clientId);
        getRegistresAdresse(clientId);
    },[,clientId]);


    return(<>
         { !visibleForm || 
        <AdresseModalForm />
    }

        <div className="container my-4 border p-4">
            <h5>Adresses Client</h5><hr />
                <div className="d-grid gap-2 d-md-flex justify-content-md-end">

                { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Adresse</button>}

                
            </div>
            <hr />
            {             
                    (registres?.length === 0)
                    ? <div className="alert alert-warning">Il n'y a pas d' adresse enregistré...</div> 
                    : 
                    <>
                    <AdresseList />
             
                    </>
                }
    </div>
        </>);

};