import { useContext } from "react";
import { UserRow } from "./UserRow";
import { UserContext } from "../../context/UserContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const UsersList = () => {

    const { users } = useContext(UserContext);
    const { paginator } = useContext(UserContext);
    const { login } = useContext(AuthContext);


    return(
    <table className="table table-hover table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Email</th>
                {!login.isAdmin ||<>
                    <th>Actualiser</th>
                    <th>Actualiser</th>
                    <th>supprimer</th>
                </>
                }
    
            </tr>
        </thead>
        <tbody>
            {

              
                users.map(user => (
                    <tr key={ user.id   }>
                        <UserRow user={ user } />
                    </tr>

                ))
            }
        </tbody>
    </table>
    );
};