import { useContext, useReducer, useState } from "react";
import { UsesReducer } from "../reducer/UsesReducer";
import  Swal  from "sweetalert2";
import { useNavigate } from "react-router-dom";
import { findAll, findAllPages, remove, save, update, findMyUser, changePass } from "../services/userService";
import { AuthContext } from "../auth/context/AuthContext";
 
const listeUsers = [];

const userEstructure = {
    id: 0,
    nameU: '',
    lastName: '',
    email: '',
    password: '',
    admin: false,
}

const initErrors = {
    nameU: '',
    lastName: '',
    email: '',
    password: '',
    //admin: false,
}

const iniPaginator = {};

export const useUsers = () => {
    const [users, dispatch] = useReducer(UsesReducer, listeUsers);
    const [paginator, dispatchPaginator] = useReducer(UsesReducer, iniPaginator);
    const [selectUser, setSelectUser] = useState(userEstructure);
    const [selectMyUser, dispatchMyUser] = useReducer(UsesReducer, userEstructure);
    const [resPass, dispatchResPass] = useReducer(UsesReducer, {id : 0, email: '',});
    const [visibleForm, setVisibleForm] = useState(false); 
    const [errors, setErrors] = useState(initErrors);
    const { login, handlerLogout } = useContext(AuthContext);

    const getUsers = async(page=0) =>{ 
        const result = await findAllPages(page); //findAll()
        dispatch({
            type: 'loadingUsers',
            payload: result.data ,
        });
        dispatchPaginator({
            type: 'loadingPagination',
            payload: result.data,
        })
    }
    const getMyUser = async(id) =>{ 
        const result = await findMyUser(id); //findAll()
        console.log("result=>: "+result.data);
        dispatchMyUser({
            type: 'loadingMyUser',
            payload: result.data ,
        });
    }
    const navigation = useNavigate();
    const handlerChangerPass = async(user) =>{
        let response;
        try {
            response = await changePass(user);
            dispatchResPass({
                type: 'changerPass',
                payload: response.data ,
            });
            Swal.fire(
                'Password Utilisateur modifié!.',
                'Password L\'utilisateur a été actualisé avec succès !',
                'success'
                )
               return true;
        } catch (error) {
         
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `${error.response?.data}`,
                footer: ''
              })
     
           
            if(error.response && error.response.status == 400 && error.response?.data?.includes('incorrect')){
                setErrors({passA: "Le mot de passe est incorrect!!"});
            }else if(error.response?.status == 401){
                handlerLogout();
            }else{
                throw error;
            }
        }


    }


    const handlerAddUser = async(user) =>{
        //const type=(user.id === 0) ? 'addUser' : 'updateUser';

        if(!login.isAdmin) return;

        let response;
        try {
            
            if(user.id === 0){
                response = await save(user);
            }else{
                response = await update(user);
            }
            
            dispatch({
  

        
                type : (user.id === 0) ? 'addUser' : 'updateUser',
                //payload: user,
                payload: response.data,
            })
            
            if(user.id === 0){
                Swal.fire(
                    'Utilisateur enregistré!.',
                    'L\'Utilisateur a été enregistré avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/users');
                }else{
                    Swal.fire(
                    'Utilisateur modifié!.',
                    'L\'Utilisateur a été actualisé avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/users');
                }
            
                
        } catch(error) {
            if(error.response && error.response.status == 400){
                setErrors(error.response.data);
            }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                setErrors({email: "L'email est déjà utilisé !"});
            }else if(error.response?.status == 401){
                handlerLogout();
            }else{
                throw error;
            }
        }
            
    }




        const handlerDelateUser = (id) =>{
            
            Swal.fire({
                title: 'Êtes-vous sûr ?',
                text: "Êtes-vous sûr de supprimer l'utilisateur ?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Oui, supprimer !'
            }).then(async(result) => {
                try {
                    if (result.isConfirmed) {
                       await remove(id);
                        
                        dispatch({
                            type: 'delUser',
                            payload: id,
                        })
                        Swal.fire(
                            'Supprimé !',
                            'L\'utilisateur a été supprimé avec succès !',
                            'success'
                            )
                        }
                    
                } catch (error) {
                    if(error.response?.status == 401){
                        handlerLogout();
                    }
                    
                }

                })
            }
            const handlerSelectUser = (user) =>{
                setSelectUser({...user});
                setVisibleForm(true);
            }
            
            const handlerFormOpen = () =>{
                setVisibleForm(true);
            }
            const handerFormClose = () =>{
                setVisibleForm(false);
                setSelectUser(userEstructure);
                setErrors({});
            }
            

            
            
            
    return {
        users,
        selectUser,
        selectMyUser,
        paginator,
        userEstructure,
        resPass,
        handlerAddUser,
        handlerDelateUser,
        handlerSelectUser,
        handlerChangerPass,
        handlerFormOpen,
        handerFormClose,
        getUsers,
        getMyUser,
        visibleForm,
        errors,
    }
};