import { useContext, useReducer, useState } from "react";
import { etablissementReducer } from "../reducer/etablissementReducer";
import { AuthContext } from "../auth/context/AuthContext";
import { findAll, findAllPages, save, update } from "../services/etablissementServices";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";



const listeEtabliss = [];

const iniPaginator = {};

const etablissEst = {
    id: 0,
    nom: '',
    siret: '',
    rue: '',
    cod_postal: '',
    ville: '',
    num_tel: '',
    createdOn: '',
    updatedOn: '',
    statut: true,
}

const initErrors = {
    nom: '',
    siret: '',
    rue: '',
    cod_postal: '',
    ville: '',
    num_tel: '',
}

export const useEtablissement = () => {
    const { login, handlerLogout } = useContext(AuthContext);
    const [selectEtablissement, setSelectEtablissement] = useState(etablissEst);
    const [etablissements, dispatch] = useReducer(etablissementReducer, listeEtabliss);
    const [paginator, dispatchPaginator] = useReducer(etablissementReducer, iniPaginator);
    const [errors, setErrors] = useState(initErrors);
    const [visibleForm, setVisibleForm] = useState(false); 
    const [optionForm, setOptionForm ] = useState("");
    const navigation = useNavigate();

    /* READ Etablissement */
    /*======================================================*/
    const getEtablissements = async(page=0) =>{ 
        const result = await findAllPages(page); //findAll()
        dispatch({
            type: 'loadingEtablissement',
            payload: result.data ,
        });
        dispatchPaginator({
            type: 'loadingEtablissementPagination',
            payload: result.data,
        })
    }
    /*END READ Etablissement */
    /*======================================================*/



    /* CREATION ET MODIFICATION Etablissement*/
    /*======================================================*/
    const handlerAddEtablissement = async(etablissement) =>{
        console.log("Entra en la guardar");
            if(!login.isAdmin) return;
            let response;
            try {
                if(etablissement.id === 0){
                    response = await save(etablissement);
                }else{
                    response = await update(etablissement);
                }
                dispatch({
                    type : (etablissement.id === 0) ? 'addEtablissement' : 'updateEtablissement',
                    payload: response.data,
                })
                if(etablissement.id === 0){
                    Swal.fire(
                        'Etablissement enregistré!.',
                        'L\'Etablissement a été enregistré avec succès !',
                        'success'
                        )
                        handerFormClose();
                        navigation('/etablissements');
                    }else{
                        Swal.fire(
                        'Etablissement modifié!.',
                        'L\'Etablissement a été actualisé avec succès !',
                        'success'
                        )
                        handerFormClose();
                        navigation('/etablissements');
                    }
            } catch(error) {
                if(error.response && error.response.status == 400){
                    setErrors(error.response.data);
                }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                    setErrors({siret: "L'siret est déjà utilisé !"});
                }else if(error.response?.status == 401){
                    handlerLogout();
                }else{
                    throw error;
                }
            }
    }
    /*END CREATION ET MODIFICATION Etablissement*/
    /*======================================================*/

    const handlerSelectEtablissement = (etablissement, option) =>{
        if(option == "edit"){
            setOptionForm(option);
        }else{
            setOptionForm(option);
        }
        setSelectEtablissement({...etablissement});
        setVisibleForm(true);
    }

    const handerFormClose = () =>{
        setVisibleForm(false);
        setSelectEtablissement(etablissEst);
        setErrors({});
        getEtablissements();
        
    }

    
    const handlerFormOpen = () =>{
        setOptionForm('new');
        setVisibleForm(true);
    }



    return{
        handlerAddEtablissement,
        handlerSelectEtablissement,
        getEtablissements,
        handlerFormOpen,
        handerFormClose,
        optionForm,
        visibleForm,
        listeEtabliss,
        selectEtablissement,
        iniPaginator,
        etablissEst,
        initErrors,
        etablissements,
        paginator,
        errors,
    }

}