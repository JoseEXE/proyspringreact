import { UseLoginAuth } from "../hooks/UseLoginAuth";
import { AuthContext } from "./AuthContext";

export const AuthProvider = ({ children }) =>{

    const {login, handlerLogin, handlerLogout } = UseLoginAuth();

    return(
        <AuthContext.Provider value={
            {
                login, 
                handlerLogin, 
                handlerLogout
            }
        }>
        { children }
        </AuthContext.Provider>
    );
}