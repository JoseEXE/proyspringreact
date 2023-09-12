import { useReducer } from "react";
import { loginReducer } from "../reducer/loginReducer";
import Swal from "sweetalert2";
import { loginUser } from "../services/authService";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { UserContext } from "../../context/UserContext";

const initLogin = JSON.parse(sessionStorage.getItem('login')) || {
    isAuth : false,
    isAdmin : false,
    user : undefined,
    id : 0,
    fullName : undefined,
}



export const UseLoginAuth = () =>{
    //const { userEstructure } = useContext(UserContext);

    const [login, dispatch] = useReducer(loginReducer, initLogin);
    //const [getUserLogin, dispatchUserLogin] = useReducer(loginReducer, userEstructure);
    const navigate = useNavigate();

    const handlerLogin = async( username, password ) =>{

        
        try{
     
            const response = await loginUser({ username, password });
            const token = response.data.token;

            const claims = JSON.parse(window.atob(token.split(".")[1]));
            const user = { username: claims.username, id: claims.id, fullName: claims.fullName  };

            dispatch({
                type: 'login',
                payload: {user, isAdmin : claims.isAdmin},
            });
            sessionStorage.setItem('login', JSON.stringify({
                isAuth : true,
                isAdmin : claims.isAdmin,
                user : user,

            }));

            console.log("claims.isAdmin: "+claims.isAdmin);
            console.log("claims.id: "+claims.id);
            console.log("claims.fullName: "+claims.fullName);
            console.log("");
            console.log("");
            console.log("");
            sessionStorage.setItem('token', `Bearer ${token}`);

            // J'essaie de rassembler toutes les informations relatives aux utilisateurs LOGIN
            // const getUser = async(username) =>{ 
            //     const result = await findByEmail(username); //findAll()
            //     console.log("result=>: "+result.data);
            //     dispatchUserLogin({
            //         type: 'getInformationLogin',
            //         payload: result.data ,
        
        
            //     });

            // }

            navigate('/users');
        }catch(error){
            if(error.response?.status == 401){
                Swal.fire("Error de validation...","Vouz devez completer le formulaire ou l'utilisateur n'est pas valide!","error");

            }else if(error.response?.status == 403){
                Swal.fire("Error de validation...","Ne dispose pas des autorisations nécessaires pour accéder aux ressources!","error");
            }else{
                throw error;
            }
        }
    }

    const handlerLogout = () =>{
        dispatch({
            type: 'logout',
        })
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('login');
        sessionStorage.clear();
    
        
    }

    return{
        login,
        handlerLogin,
        handlerLogout,

    }
}