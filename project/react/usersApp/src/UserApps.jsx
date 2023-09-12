import { LoginPages } from "./auth/pages/LoginPages";
import { Navigate, Route, Routes } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "./auth/context/AuthContext";
import { UserRoutes } from "./routes/UserRoutes";

export const UserApps = () => {
   
    const { login } = useContext(AuthContext);

    return(
        <Routes>
            {
            login.isAuth ? 
            (
                <Route path="/*" element={ <UserRoutes /> }/>
            ) :
            <>
            <Route path='/login' element={<LoginPages />} />
            <Route path='/*' element={ <Navigate to="/login" /> } />

            </>
                 
 
            }
        
        </Routes>
    );
};