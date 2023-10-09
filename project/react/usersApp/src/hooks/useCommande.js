import { useContext, useReducer, useState } from "react";
import { AuthContext } from "../auth/context/AuthContext";
import { useNavigate } from "react-router-dom";
import { findLast, save } from "../services/commandeService";
import { commandeReducer } from "../reducer/commandeReducer";

const initTypePaiment = ["Ticket Resto", "Carte Bancaire", "Espèces"];

const initListe = [];

const iniPaginator = {};

const initEstructure = {
   
    id : 0,
    client :{
        id: 0,
    },
    user : {
        id: 0,
    },
    adresse :{
        id: 0,
    },
    commentaire : '',
    totalHl : 0,
    total : 0,
    type_paiement : '',
    etat : '',
}

const initErrors = {
}





export const useCommande = () =>{

    const { login, handlerLogout } = useContext(AuthContext);
    const [selectRegistre, setSelectRegistre] = useState(initEstructure);
    const [visibleForm, setVisibleForm] = useState(false); 
    const [errors, setErrors] = useState(initErrors);
    const [registres, dispatch] = useReducer(commandeReducer, initListe);
    const [paginator, dispatchPaginator] = useReducer(commandeReducer, iniPaginator);
    const navigation = useNavigate();


    /* READ Registers */
    /*======================================================*/
    const getRegistres = async(page=0) =>{ 
        const result = await findAllPages(page); //findAll()
        dispatch({
            type: 'loading',
            payload: result.data ,
        });
        dispatchPaginator({
            type: 'loadingPagination',
            payload: result.data,
        })
    }
    /*END READ Registers */
    const getRegistresLast = async() =>{ 
        const result = await findLast(); 

            dispatchLast({
                type: 'loadingLast',
                payload: result.data,
            });
    }
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
                    'Cat Produit enregistré!.',
                    'L\'Cat Produit a été enregistré avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/catproduits');
                }else{
                    Swal.fire(
                    'Cat Produit modifié!.',
                    'L\'Cat Produit a été actualisé avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/catproduits');
                }
                    
                    
            } catch(error) {
                if(error.response && error.response.status == 400){
                    setErrors(error.response.data);
                }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                    setErrors({nom: "L'nom cat produit est déjà utilisé !"});
                }else if(error.response?.status == 401){
                    handlerLogout();
                }else if(error.response.status == 403){
                    setErrors({nom: "L'nom cat produit est déjà utilisé ! "});
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
        getRegistres();
        
    }

    
    const handlerFormOpen = () =>{
        setOptionForm('new');
        setVisibleForm(true);
    }



    
    return{
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistres,
        handlerFormOpen,
        handerFormClose,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        paginator,
        errors,
        initTypePaiment,
    }



}