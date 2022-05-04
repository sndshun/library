import ajaxRequest from "../utils/ajaxRequest"

export function getBannerList() {
  return ajaxRequest({
    url: "attachment/banner",
    method: "get",
  })
}