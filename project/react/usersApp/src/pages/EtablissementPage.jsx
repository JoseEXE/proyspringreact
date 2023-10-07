import { useParams } from "react-router-dom";
import { EtablissementForm } from "../components/etablissement/EtablissementForm";
import { EtablissementList } from "../components/etablissement/EtablissementList";
import { EtablissementContext } from "../context/etablissement/EtablissementContext";
import { useContext, useEffect } from "react";
import { AuthContext } from "../auth/context/AuthContext";
import { Paginator } from "../components/Paginator";
import { EtablissementModalForm } from "../components/etablissement/EtablissementModalForm";

export const EtablissementPage = () => {

  const { page } = useParams();

  
  const {
    etablissements,
    paginator,
    getEtablissements,
    visibleForm,
    handlerFormOpen,
} = useContext(EtablissementContext);

const { login } = useContext(AuthContext);

useEffect(()=>{
  getEtablissements(page);
},[,page])


    return (<>
    { !visibleForm || 
        <EtablissementModalForm />
    }


    <div className="container my-4 border p-4">
    <h3>Etablissements</h3><hr />
        <div className="d-grid gap-2 d-md-flex justify-content-md-end">

        { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Etablissement</button>}

        
      </div>
      <hr />
      {             
                    (etablissements?.length === 0)
                    ? <div className="alert alert-warning">Il n'y a pas d'etablissements enregistré...</div> 
                    : 
                    <>
                    <EtablissementList />
                    <Paginator url="/etablissements/page" paginator={paginator} />
                    </>
                }
    </div>

    </>);
}