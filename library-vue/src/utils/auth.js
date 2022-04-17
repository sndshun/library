/**
 * 过期时间7天
 * @type {number}
 */
const clearTime=7
const library = "library"

export function setLibrary(value) {
  sessionStorage.setItem(library, value)
}

export function getLibrary() {
  return sessionStorage.getItem(library)
}

export function clearLibrary() {
  sessionStorage.clear()
}

export function setRememberMeLibrary(value) {
  const date=new Date()
  date.setDate(date.getDate()+clearTime)
  const data={
    value:value,
    date:date
  }
  localStorage.setItem(library, JSON.stringify(data))
}

export function getRememberMeLibrary() {
  const str=localStorage.getItem(library)
  if (null==str) {
    return null
  }

  const d=JSON.parse(str);
  if (new Date() >= new Date(d.date)) {
    clearRememberMeLibrary();
    return localStorage.getItem(library)
  } else {
    return d.value
  }

}

export function clearRememberMeLibrary() {
  localStorage.clear()
}




