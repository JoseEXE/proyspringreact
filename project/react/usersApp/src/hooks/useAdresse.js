import { useContext, useReducer, useState } from "react";
import { AuthContext } from "../auth/context/AuthContext";
import { useNavigate } from "react-router-dom";
import { adresseReducer } from "../reducer/adresseReducer";
import { findByClient, save, update } from "../services/adresseService";

const initListe = [];

const iniPaginator = {};

const initEstructure = {
    id: 0,
    client: {
        id: 0,
    },
    rue: '', 
    cod_postal: 0, 
    ville: '', 
    complement: '', 
    statut: true,


}

const initErrors = {
    rue: '', 
    cod_postal: '', 
    ville: '', 
    complement: '', 
}

export const useAdresse = () =>{
    const { login, handlerLogout } = useContext(AuthContext);

    const [selectRegistre, setSelectRegistre] = useState(initEstructure);
    const [errors, setErrors] = useState(initErrors);
    const [visibleForm, setVisibleForm] = useState(false); 
    const [optionForm, setOptionForm ] = useState("");
    const [clientActive, setClientActive ] = useState(0);
    const navigation = useNavigate();

    const [registres, dispatch] = useReducer(adresseReducer, initListe);


    /* READ Registers */
    /*======================================================*/
    const getRegistresAdresse = async(id) =>{ 
       let client = {
                id : id,
       }

        const result = await findByClient(client); 
        dispatch({
            type: 'loading',
            payload: result.data ,
        });
    }
    /*END READ Registers */
    /*======================================================*/

     /* CREATION ET MODIFICATION Etablissement*/
    /*======================================================*/
    const handlerAddRegistre = async(register) =>{
            if(!login.isAdmin) return;

            let response;
            try {
         
                
                if(register.id === 0){
                    response = await save(register);
                }else{
                    response = await update(register);
                }
                
                dispatch({
        
                    type : (register.id === 0) ? 'addRegistre' : 'updateRegistre',
                    payload: response.data,
                })
              
     
                if(register.id === 0){
                Swal.fire(
                    'Adresse de client enregistré!.',
                    'L\'Adresse de client a été enregistré avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/adresses');
                }else{
                    Swal.fire(
                    'Adresse de client modifié!.',
                    'L\'Adresse de client a été actualisé avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/adresses');
                }
                    
                    
            } catch(error) {
                if(error.response && error.response.status == 400){
                    setErrors(error.response.data);
                }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                    setErrors({nom: "L'Adresse de client  est déjà utilisé !"});
                }else if(error.response?.status == 401){
                    handlerLogout();
                }else if(error.response.status == 403){
                    setErrors({nom: " déjà utilisé ! ????"});
                }else{
                    throw error;
                }
            }
    }
    /*END CREATION ET MODIFICATION Registre*/
    /*======================================================*/

    const handlerSelectRegistre = (registre, option) =>{
        if(option == "edit"){
            setOptionForm(option);
        }else{
            setOptionForm(option);
        }
        setSelectRegistre({...registre});
        setVisibleForm(true);
    }

    const handerFormClose = () =>{
        setVisibleForm(false);
        setSelectRegistre(initEstructure);
        setErrors({});
        getRegistresAdresse();
        
    }

    
    const handlerFormOpen = () =>{
        setOptionForm('new');
        setVisibleForm(true);
    }



    const handlerIdClientActive = (clientId) =>{
        setClientActive(clientId);
    }



    return{
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistresAdresse,
        handlerFormOpen,
        handerFormClose,
        optionForm,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        errors,
        handlerIdClientActive,
        clientActive,
    }


}