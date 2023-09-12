import { useContext } from "react";
import { EtablissementContext } from "../../context/etablissement/EtablissementContext";
import { AuthContext } from "../../auth/context/AuthContext";
import { EtablissementRows } from "./EtablissementRows";

export const EtablissementList = () => {


    const { etablissements } = useContext(EtablissementContext);
    const { paginator } = useContext(EtablissementContext);
    const { login } = useContext(AuthContext);    

    return(
        <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>rue</th>
                        <th>Code postal</th>
                        <th>created</th>
                        {!login.isAdmin ||<>
                            <th></th>
                            <th></th>
                        </>
                        }
            
                    </tr>
                </thead>
                <tbody>
                {

              
        etablissements.map(etablissement => (
            <tr key={ etablissement.id   }>
                <EtablissementRows etablissement={ etablissement } />
            </tr>

))
}
</tbody>
</table> );

};