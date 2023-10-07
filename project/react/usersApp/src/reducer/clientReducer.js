export const clientReducer = (state = {}, action) =>{

    switch (action.type) {
        case 'addClient':
            return [
                ...state,
                {	
                    ...action.payload,
                }
            ];
        
        case 'updateClient':
            return state.map(u => {
                if(u.id === action.payload.id){
                    return {
                        ...action.payload
                    };
                }
                return u;
            });

        case 'loadingClient':
            return action.payload.content;

        case 'loadingClientByTel':
            return action.payload;
        
        case 'loadingClientPagination':
            return action.payload;  
        
        default:
            return state;
    }

}