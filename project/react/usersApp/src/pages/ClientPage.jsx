import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { AuthContext } from "../auth/context/AuthContext";
import { ClientContext } from "../context/client/ClientContext";
import { ClientModalForm } from "../components/client/ClientModalForm";
import { ClientList } from "../components/client/ClientList";
import { Paginator } from "../components/Paginator";
import { ClientListRe } from "../components/client/ClientListRe";

export const ClientPage = () => {

    const {handerFormClose } = useContext(ClientContext); 
    const [useNumTel, setUseNumTel] = useState("");
    const [useRecherche, setUseRecherche] = useState(0);

    const { page } = useParams();
    const { login } = useContext(AuthContext);

    const {
        registres,
        paginator,
        getRegistres,
        visibleForm,
        handlerFormOpen,
        handlerRecherche,
        registresRecherche,
    } = useContext(ClientContext);

    useEffect(()=>{
        getRegistres(page);
        //getAdresses
    },[,page]);

    const onRechercheTel = ({ target }) => {
        const { name, value } = target;
        
        setUseNumTel(value)        
    }
    

    function onSubmit(event) {
        event.preventDefault();
        setUseRecherche(1);
        handlerRecherche(useNumTel);
    }

    const onListTout = () => {
        setUseRecherche(0);

      }


    return(<>
    { !visibleForm || 
        <ClientModalForm />
    }

        <div className="container my-4 border p-4">
            <h3>Clients</h3><hr />
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
            <button className="btn btn-primary btn-sm " type="submit" onClick={ onListTout }><i class="bi bi-list"></i> &nbsp; Lister tout  </button>

                { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Client</button>}

            </div>
            <hr />
            <form onSubmit={ onSubmit }>
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
             
                    <label className="control-label   my-1"><strong><i>Recherche de clients par numéro de téléphone : </i></strong></label> 
                    <input type="text" className="form-control w-25 " placeholder="Numéro tétéphone" name="tel" onChange={ onRechercheTel } />
                    <button className="btn btn-primary btn-sm " type="submit"><i class="bi bi-search"></i> &nbsp; Recherche Client  </button>
            </div>
            </form>
            <hr />
            {  
            
            (useRecherche == 0)
            ?
                (registres?.length === 0)
                ? <div className="alert alert-warning">Il n'y a pas Clients enregistré...</div> 
                : 
                <>
                <ClientList />
                <Paginator url="/clients/page" paginator={paginator} />
                </>
            :
                (registresRecherche?.length === 0)
                ? <div className="alert alert-warning">Aucun enregistrement n'a été trouvé...</div> 
                : 
                <>
                <ClientListRe />
                </>

            
            

            }
    </div>


    </>);
};