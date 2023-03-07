
/**
 * 1.一次性引入某个文件夹下的所有文件
 * require.context(directory,useSubdirectories,regExp)
 * directory : 需要引入文件的目录
 * useSubdirectories : 是否查找该目录下的子目录
 * regExp : 匹配引入文件的正则表达式
 */
const req = require.context('../../../assets/icons/svg', false, /\.svg$/)
const requireAll = requireContext => requireContext.keys()

const re = /\.\/(.*)\.svg/

const svgIcons = requireAll(req).map(i => {
  return i.match(re)[1]
})

export default svgIcons
