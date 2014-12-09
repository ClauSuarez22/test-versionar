import groovy.json.JsonBuilder

class VersionarController {

    def test() { 

    	def json = request.JSON;

    	withJGit() { rf ->
		    pull().call()
		    def f = new File("configurations/seo_configuration_MLU.json");
		    f.write(new JsonBuilder(json).toPrettyString())
		    f.createNewFile()
		    
		    // Relative path
		    add().addFilepattern(f.name).call()
		    commit().setMessage("Versionando MCO1").call()
		    push().call()
		    pull().call()
		}
		render "listo"
    }
}
