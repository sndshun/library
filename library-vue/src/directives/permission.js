import {userStore} from "../store";

export default {
    beforeMount(el, binding) {
        const {userPermission} = userStore()
        const {value} = binding;
        if (value && !userPermission.includes(value)) {
            el.style.display = 'none'
        }
    },
};
