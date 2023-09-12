import { useContext, useEffect, useState } from "react";
import { EtablissementContext } from "../context/etablissement/EtablissementContext";
import { useParams } from "react-router-dom";
import { EtablissementForm } from "../components/etablissement/EtablissementForm";

export const EtablissementRegPage = () =>{

    const { etablissements=[], etablissEst } = useContext(EtablissementContext);
    const [selectEtablissement, setSelectEtablissement] = useState(etablissEst);

    const { id } = useParams();
    useEffect(() =>{
        console.log("id: "+id);
        if(id){
            const etablissementx = etablissements.find(u => u.id == id ) || etablissEst;
            setSelectEtablissement(etablissementx);
        }
        
    }, [id])


    return(<>
    <div className="container my-4">
    <div className="row justify-content-center">
        <div className="col-12 col-md-8 col-lg-8 col-xl-6 shadow p-3 mb-5 bg-body rounded">
        <div className="col text-center">
        <h3>{ (selectEtablissement.id > 0) ? 'Edition' : 'Enregistrement' } de l'Etablissement</h3>
              <hr/>
        </div>
        <div className="row">
            <div className="col"> 
                <EtablissementForm  selectEtablissement={ selectEtablissement } />
            </div>
        </div>
    </div>
    </div>
    </div>
    </>);
}