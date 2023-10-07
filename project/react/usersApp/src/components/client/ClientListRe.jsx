import { useContext } from "react";
import { ClientContext } from "../../context/client/ClientContext";
import { AuthContext } from "../../auth/context/AuthContext";
import { ClientRows } from "./ClientRows";

export const ClientListRe = () => {

    const { registresRecherche } = useContext(ClientContext);
    const { paginator } = useContext(ClientContext);
    const { login } = useContext(AuthContext);    

    console.log("registresRecherche: "+registresRecherche?.length);
    return(      
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th className="text-center">Numéro Téléphone</th>
                    {!login.isAdmin ||<>
                        <th></th>
                        <th></th>
                    </>
                    }
                </tr>
            </thead>
            <tbody>
            {
 

                registresRecherche.map(registre => (
                <tr key={ registre.id   }>
                    <ClientRows registre={ registre } />
                </tr>
            ))
            }
            </tbody>
            </table> 
    );
} 