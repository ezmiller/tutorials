(ns scratch
  (:require [alembic.still :refer [distill]]
            [clojure.walk :as walk]))

(->> '[[cnuernber/libpython-clj "1.2"]
       [panthera "0.1-alpha.16"]
       [metosin/jsonista "0.2.4"]]
     (map distill)
     doall)


(require '[clojure.walk :as walk]
         '[clojure.string :as string])


;; Ipynb to clj:
;; (requires cheshire)
#_(-> "panthera-test.ipynb"
    slurp
    (cheshire.core/parse-string true)
    :cells
    (->> (mapcat :source)
         (string/join "\n")
         (spit "draft.clj")))


(ns panthera-test
  (:require
   [libpython-clj.python :as py]
   [panthera.panthera :as pt]
   [panthera.numpy :as np]
   [panthera.pandas.utils :as u]
   [clojure.pprint :refer [pprint]]
   [clojure.repl :refer [doc]]))


(py/initialize!)



;; Adapting Panthera's module function to avoid key conversions.
(defn module
  [py-module]
  (fn [x]
    (fn
      ([]
       (if (seqable? x)
         (let [ks x]
           (py/get-attr (np/py-get-in py-module ks) (last ks)))
         (py/get-attr py-module x)))
      ([attrs]
       (if (seqable? x)
         (let [ks x]
           (py/call-attr-kw (np/py-get-in py-module ks) (last ks)
                            (vec (:args attrs))
                            (u/keys->pyargs (dissoc attrs :args))))
         (py/call-attr-kw py-module x
                          (vec (:args attrs))
                          (u/keys->pyargs (dissoc attrs :args))))))))



;; Wrapping Scikit Learn (sklearn)

(defonce sk (py/import-module "sklearn"))

(py/run-simple-string "from sklearn import *")

(defn sklearn
  ([k] ((module sk) k))
  ([k args] (((module sk) k) args)))

(def sample-data (sklearn [:datasets :make_multilabel_classification]
                          {:random-state 0 }))

(def X (first sample-data))

(doc py/call-attr)

(py/python-type X)

(pprint (take 10 X))

(def decomp ((sklearn [:decomposition])))

(def lda ((sklearn [:decomposition :LatentDirichletAllocation])))

;; Wrapping Gensim

(defonce gs (py/import-module "gensim"))

(py/run-simple-string "from gensim.test.utils import *")

(pprint (py/att-type-map gs))

(defn gensim
  ([k] ((module gs) k))
  ([k args] (((module gs) k) args)))

(def common-texts (gensim [:test :utils :common_texts]))

(common-texts)

(def Dictionary (-> (py/get-attr gs "corpora")
                    (py/get-attr "dictionary")
                    (py/get-attr "Dictionary")))

(def common-dictionary (Dictionary (common-texts)))

(type common-dictionary)

(def common-corpus (for [t (common-texts)] (py/call-attr common-dictionary "doc2bow" t)))

(map type common-corpus)

(type common-corpus)

common-corpus

(def gensim-models (gensim [:models]))

(def LdaModel (gensim [:models :LdaModel]))

(type common-corpus)

(py/call-attr (gensim-models) "LdaModel" common-corpus)


;; For loop of python dictionaries might be a good minimal example for Chris.

(doc py/call-attr-kw)

(py/call-attr-kw (gensim-models) "LdaModel" common-corpus { "num_topics" 5})

