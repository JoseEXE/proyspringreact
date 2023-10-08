import { useContext } from "react";
import { AuthContext } from "../../auth/context/AuthContext";
import { AdresseContext } from "../../context/adresse/AdresseContext";
import { AdresseRows } from "./AdresseRows";

export const AdresseList = () =>{

    const { registres } = useContext(AdresseContext);
    const { login } = useContext(AuthContext);    


    return(<>
 
    <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Rue</th>
                        <th>Cod Postal</th>
                        <th>Ville</th>
                        <th>Complement</th>

                        {!login.isAdmin ||<>
                            <th></th>
                            <th></th>
                        </>
                        }
                    </tr>
                </thead>
                <tbody>
                {     
        registres.map(registre => (
            <tr key={ registre.id   }>
                <AdresseRows registre={ registre } />
            </tr>
))
}
</tbody>
</table> 
    </>);
}