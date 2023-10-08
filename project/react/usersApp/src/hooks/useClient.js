import { useContext, useReducer, useState } from "react";
import { AuthContext } from "../auth/context/AuthContext";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { clientReducer } from "../reducer/clientReducer";
import { findAllPages, findByTel, save, update } from "../services/clientService";
import { AdresseContext } from "../context/adresse/AdresseContext";
import { adresseReducer } from "../reducer/adresseReducer";

const initListe = [];

const iniPaginator = {};

const initEstructure = {
    id : 0,
    nom : "",
    prenom : "",
    tel : "",
    createdOn : "",
    updatedOn : "",
    statut : true,
}

const initErrors = {
    nom : "",
    prenom : "",
    tel : "",
}

export const useClient = () =>{

    /* useContext */
    const { login, handlerLogout } = useContext(AuthContext);
    //const { getRegistresAdresse } = useContext(AdresseContext)
    /* useState */
    const [selectRegistre, setSelectRegistre] = useState(initEstructure);
    const [errors, setErrors] = useState(initErrors);
    const [visibleForm, setVisibleForm] = useState(false); 
    const [optionForm, setOptionForm ] = useState("");
    const [VisibleregistresAdresseClient, setVisibleregistresAdresseClient] = useState(0); 
    /* useReducer */
    const [registres, dispatch] = useReducer(clientReducer, initListe);
    const [registresRecherche, dispatchRecherche] = useReducer(clientReducer, initListe);
    const [paginator, dispatchPaginator] = useReducer(clientReducer, iniPaginator);
 
    /* === */
    const navigation = useNavigate();

    /* READ Registers */
    /*======================================================*/
    const getRegistres = async(page=0, tel) =>{ 
      
            const result = await findAllPages(page); //findAll()
           
            dispatch({
                type: 'loadingClient',
                payload: result.data,
            });
            dispatchPaginator({
                type: 'loadingClientPagination',
                payload: result.data,
            })
    }

    const getRegistresRecherche = async(tel) =>{ 

        if(tel != undefined || tel != ""){
            const result = await findByTel(tel); 
            console.log("====> result?.length: "+result?.length);
            dispatchRecherche({
                type: 'loadingClientByTel',
                payload: result.data,
            });
        }
    }


    /*END READ Registers */
    /*======================================================*/
    /* CREATION ET MODIFICATION Produit*/
    /*======================================================*/
    const handlerAddRegistre = async(register) =>{
        if(!login.isAdmin) return;
        let response;
        try {
         // console.log(JSON.parse(JSON.stringify(register)));

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
                'Client enregistré!.',
                'Le Client a été enregistré avec succès !',
                'success'
                )
                handerFormClose();
                navigation('/clients');
            }else{
                Swal.fire(
                'Client modifié!.',
                'Le Client a été actualisé avec succès !',
                'success'
                )
                handerFormClose();
                navigation('/clients');
            }
                
                
        } catch(error) {
            if(error.response && error.response.status == 400){
                setErrors(error.response.data);
            }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                setErrors({nom: "Le client est déjà enregistré !"});
            }else if(error.response?.status == 401){
                handlerLogout();
            }else if(error.response.status == 403){
                Swal.fire(
                    'Client !.',
                    'Le numéro de téléphone semble exister dans notre base de données. !',
                    'error'
                    )


                
            }else{
                throw error;
            }
        }
    }
    /*END CREATION ET MODIFICATION Registre*/
    /*======================================================*/

    const handlerRecherche = ( numTel ) =>{
        
        let tel = {
            tel : numTel,
        }

        setVisibleregistresAdresseClient(0);
        getRegistresRecherche(tel);

    }

    const handlerSelectRegistre = (registre, option) =>{      
        setOptionForm(option);
        setSelectRegistre({...registre});
        setVisibleForm(true);
    }

    const handlerSelectClientAdresse = (clientSelected) =>{
        setVisibleregistresAdresseClient(clientSelected);
    }

    const handerFormClose = () =>{
        setVisibleForm(false);
        setSelectRegistre(initEstructure);
        setErrors({});
        setVisibleregistresAdresseClient(0);
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
        optionForm,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        paginator,
        errors,
        handlerRecherche,
        registresRecherche,
        handlerSelectClientAdresse,
        VisibleregistresAdresseClient,
    }
}