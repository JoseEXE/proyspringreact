import { AdresseContext } from "../context/adresse/AdresseContext";
import { ClientContext } from "../context/client/ClientContext";
import { CommandeContext } from "../context/commande/CommandeContext";

export const CommandePage = () =>{

    const {
        registres,
        paginator,
        getRegistres,
        visibleForm,
        handlerFormOpen,
    } = useContext(CommandeContext);

    const { VisibleregistresAdresseClient } = useContext(ClientContext);

    const { adresseActive } = useContext(AdresseContext);




    return(<>
     { !visibleForm || 
        <CommandeModalForm />
        }

        <div className="container my-4 border p-4">
            <h3>Commande en cours...</h3><hr />
                <div className="d-grid gap-2 d-md-flex justify-content-md-end">

                {/* { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Catègorie Produit</button>} */}

            </div>
            <hr />
                {             
                    (registres?.length === 0)
                    ? <div className="alert alert-warning">Il n'y a pas de Commande en cours ...</div> 
                    : 
                    <>
                    <CommandeList />
                    </>
                }
    </div>

    
    </>);
}