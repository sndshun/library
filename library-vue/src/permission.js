import router, {baseRouters} from "./router";
import {sysStore, userStore} from "./store";
import {getLibrary, getRememberMeLibrary} from "./utils/auth";

const whiteRouter = baseRouters.map(item => {
    return item.path
})
router.beforeEach(async (to, from, next) => {
    const {userInfo, getInfo} = userStore()
    const {setBreadcrumb} = sysStore()

    const library = getLibrary() || getRememberMeLibrary()
    if (to.meta.title) {
        document.title = to.meta.title
    }

    setBreadcrumb({
        name: to.meta.title,
        path: to.path,
    })


    if (whiteRouter.indexOf(to.path) > -1) {
        if (library && (!userInfo)) {
            await getInfo()
        }
        next()
    } else if (library) {
        if (userInfo) {
            next()
        } else {
            await getInfo();
            next(to)
        }
    } else {
        next('/login')
    }

})